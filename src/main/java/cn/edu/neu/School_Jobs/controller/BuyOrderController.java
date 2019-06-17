package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.service.*;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinHistoryFundJoinFundVo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/buy_order")
public class BuyOrderController {

    @Autowired
    BuyOrderService buyOrderService;
    @Autowired
    private FundService fundService;
    @Autowired
    private SellOrderService sellOrderService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisServer redisServer;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findBuyOrder(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BuyOrder> list = buyOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }


    @PostMapping("/add")
    public JSONObject addBuyOrder(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        try {
            int userId = Jwt.getUserId(request);
            //错误次数达到上上限
            if (userInfoService.lockPayPassword(userId)) {
                return CommonUtil.errorJson(ErrorEnum.E_794);
            }
            String pay_password = requestJson.getString("pay_password");
            // 强制类型转换的时候失败是由于字符串
            BuyOrder buyOrder = JSONObject.toJavaObject(requestJson, BuyOrder.class);
            buyOrder = buyOrderService.initialBuyOrderForInsert(buyOrder, userId);
            //        计算手续费
            Fund fund = fundService.findById(buyOrder.getFundId());
            buyOrder.setServiceCharge(fund.getBuyingRate() * buyOrder.getTransactionAmount());
            // 如果买的金额小于1元，则返回错误
            if (buyOrder.getTransactionAmount() < 10) {
                return CommonUtil.errorJson(ErrorEnum.E_780);
            }
            // 储存买入信息
            if (userInfoService.selectByIdAndPayPassword(String.valueOf(userId), userInfoService.getEncryPayPassword(pay_password)) != 0) {
                buyOrderService.save(buyOrder);
                return CommonUtil.successJson();
            }
            return CommonUtil.errorJson(userInfoService.addLockPayPassword(userId));
//            return CommonUtil.errorJson(ErrorEnum.E_784);
        } catch (Exception e) {
            return CommonUtil.errorJson(ErrorEnum.E_781);
        }


    }

    // 展示自己买的订单
    @GetMapping("/show_buy/{page}")
    public JSONObject show_buy(HttpServletRequest request, @PathVariable(value = "page") int page) {
        int userId = Jwt.getUserId(request);
        PageHelper.startPage(page, 5);
        PageInfo pageInfo = new PageInfo(buyOrderService.findOrdersWithFundInfo(userId));
        return CommonUtil.successJson(pageInfo);
    }


    // 返回自己基金数量
    @GetMapping("/show_has_funds/count")
    public JSONObject show_has_funds_count(HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        List<BuyOrderJoinHistoryFundJoinFundVo> buyOrderJoinHistoryFundJoinFundVos = buyOrderService.findAllHasBuyFund(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hasFundCount", buyOrderJoinHistoryFundJoinFundVos.size());
        return CommonUtil.successJson(jsonObject);
    }

    // 返回自己的交易次数
    @GetMapping("/show_busin/count")
    public JSONObject show_busin_count(HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("businCount", buyOrderService.findOrdersWithFundInfo(userId).size() + sellOrderService.findOrdersWithFundInfo(userId).size());
        return CommonUtil.successJson(jsonObject);
    }

    // 展示自己有什么基金
    @GetMapping("/show_has_funds/{page}")
    public JSONObject show_has_funds(HttpServletRequest request, @PathVariable(value = "page") int page) {
        int userId = Jwt.getUserId(request);
        // 分页器
        PageHelper.startPage(page, 5);
        // 得到该客户的基金资料
        List<BuyOrderJoinHistoryFundJoinFundVo> buyOrderJoinHistoryFundJoinFundVos = buyOrderService.findAllHasBuyFund(userId);
        // 设置客户昨日总收益为0
        float yestodayGetMoney = 0;
        for (BuyOrderJoinHistoryFundJoinFundVo buyOrderJoinHistoryFundJoinFundVo : buyOrderJoinHistoryFundJoinFundVos) {
            // 计算昨日收益
            // 历史价格
            String[] prices = buyOrderJoinHistoryFundJoinFundVo.getHistoryPrice().split("-");
            // 历史价格的最后一个和倒数第二个的价格差
            Float computePrice = Float.parseFloat(prices[prices.length - 1]) - Float.parseFloat(prices[prices.length - 2]);
            // 客户总收益 加上 客户昨天这个基金的收益（价格差×当前这个基金的份额）
            yestodayGetMoney += computePrice * buyOrderJoinHistoryFundJoinFundVo.getThisFundShare();
            // 设置这个基金的当前净值
            buyOrderJoinHistoryFundJoinFundVo.setWillMoney(Float.parseFloat(prices[prices.length - 1]));
            // 设置这个基金最新净值和第二新的净值的价格差以便计算该基金昨日收益
            buyOrderJoinHistoryFundJoinFundVo.setHistoryPrice(computePrice.toString());
            // 置空没有必要的数据
            buyOrderJoinHistoryFundJoinFundVo.setDate(null);
            buyOrderJoinHistoryFundJoinFundVo.setHistoryRate(null);
        }
        PageInfo pageInfo = new PageInfo(buyOrderJoinHistoryFundJoinFundVos);
        JSONObject jsonObject = new JSONObject();
        // 放数据
        jsonObject.put("yestodayGetMoney", yestodayGetMoney);
        jsonObject.put("list", pageInfo);
        return CommonUtil.successJson(jsonObject);
    }

    /**
     * @author fzb
     * @date 2019/5/30
     * @description: 已经取消卖出未确定的基金的价值
     */
    @GetMapping("/show/buy_amount")
    public JSONObject show_buy_amount(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        /**
         * 现在有什么价值： 持有基金的净值，买入未确定的基金价钱，卖出未确定的基金的价值
         */
        int userId = Jwt.getUserId(request);

        // 买入未确定的基金价钱
        HashMap not_sure_buy = buyOrderService.getSumByBuyMoney(userId);

        // 买入确认后的基金最新价格以及其信息
        List<BuyOrderJoinHistoryFundJoinFundVo> buyOrderJoinHistoryFundJoinFundVos = buyOrderService.selectOrdersLeftJoinHistoryFundByField(String.valueOf(userId), String.valueOf(1));
        float sureMoney = 0;
        // 累计计算每个已确定订单的最新价格
        for (BuyOrderJoinHistoryFundJoinFundVo buyOrderJoinHistoryFundJoinFundVo : buyOrderJoinHistoryFundJoinFundVos) {
            // 如果份额大于0的话
            if (buyOrderJoinHistoryFundJoinFundVo.getResidualShare() > 0) {
                // 净值为prices,最后一个值为最新净值
                String[] prices = buyOrderJoinHistoryFundJoinFundVo.getHistoryPrice().split("-");
                // 剩余份额*最新净值-服务费
                // 2019 05 30更新 不用减去手续费
                sureMoney += buyOrderJoinHistoryFundJoinFundVo.getResidualShare() * Float.parseFloat(prices[prices.length - 1]);
            }
        }

        // 设定原价值为0
        float notSureSell = 0;
        // 卖出未确定基金的价值，份额*最新净值
//        List<SellOrderJoinHistoryFund> sellOrderJoinHistoryFunds = sellOrderService.selectOrderWithHistoryFundByField(String.valueOf(userId),String.valueOf(0));
//        for(SellOrderJoinHistoryFund sellOrderJoinHistoryFund:sellOrderJoinHistoryFunds){
//            // 基金的历史净值表
//            String[] prices = sellOrderJoinHistoryFund.getHistoryPrice().split("-");
//            notSureSell += sellOrderJoinHistoryFund.getSellShare()*Float.parseFloat(prices[prices.length-1]);
//        }
        // 全部买入的价值
        float from_buy_order_money = 0;
        if (not_sure_buy != null) {
            from_buy_order_money = Float.parseFloat(not_sure_buy.get("sumBuy").toString()) + sureMoney + notSureSell;
        } else {
            from_buy_order_money = sureMoney + notSureSell;
        }
        jsonObject.put("getAllAmount", from_buy_order_money);
        return CommonUtil.successJson(jsonObject);
    }


    @PostMapping("/update")
    public JSONObject updateBuyOrder(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        if (userInfoService.lockPayPassword(userId)) {
            return CommonUtil.errorJson(ErrorEnum.E_794);
        }
        String payPassword = userInfoService.getEncryPayPassword(requestJson.get("payPassword").toString());
        if (userInfoService.selectByIdAndPayPassword(String.valueOf(userId), payPassword) == 0) {
            return CommonUtil.errorJson(userInfoService.addLockPayPassword(userId));
            //            return CommonUtil.errorJson(ErrorEnum.E_784);
        }
        BuyOrder buyOrder = JSONObject.toJavaObject(requestJson, BuyOrder.class);
        buyOrderService.update(buyOrder);
        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteBuyOrder(@PathVariable(value = "id") int id) {
        buyOrderService.deleteById(id);
        return CommonUtil.successJson();
    }


}
