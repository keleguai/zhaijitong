package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.service.FundService;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
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
            // 强制类型转换的时候失败是由于字符串
            BuyOrder buyOrder = JSONObject.toJavaObject(requestJson, BuyOrder.class);
            int userId = Jwt.getUserId(request);
            buyOrder = buyOrderService.initialBuyOrderForInsert(buyOrder, userId);
            //        计算手续费
            Fund fund = fundService.findById(buyOrder.getFundId());
            buyOrder.setServiceCharge(fund.getBuyingRate() * buyOrder.getTransactionAmount());
            // 如果买的金额小于1元，则返回错误
            if (buyOrder.getTransactionAmount() < 1) {
                return CommonUtil.errorJson(ErrorEnum.E_780);
            }
            // 储存买入信息
            buyOrderService.save(buyOrder);
            return CommonUtil.successJson();
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

    @GetMapping("/show/buy_amount")
    public JSONObject show_buy_amount(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        int userId = Jwt.getUserId(request);
        // 买入未确认所花费的钱
        HashMap not_sure_buy = buyOrderService.getSumByBuyMoney(userId);

        // 买入确认后剩余份额*净值-手续费的钱
        HashMap sure_buy = buyOrderService.getSumByNetMoney(userId);
        // 确定卖出的基金
        HashMap sure_sell = sellOrderService.selectHasSellMoney(userId);
        //  收益率为（目前拥有的基金+已经卖出的基金）的总价格/总共已经花的总价格
        float rate = userInfoService.getHistoryRate(userId,1024);
        // 确定卖出的基金+现在已经确定购买的基金+
        float from_buy_order_money = 0;
        // 买的订单所拥有的钱
        if (sure_buy != null && not_sure_buy != null) {
            from_buy_order_money = Float.parseFloat(sure_buy.get("sumBuy").toString()) + Float.parseFloat(not_sure_buy.get("sumBuy").toString());
        } else if (not_sure_buy != null || sure_buy != null) {
            from_buy_order_money = sure_buy != null ? Float.parseFloat(sure_buy.get("sumBuy").toString()) : Float.parseFloat(not_sure_buy.get("sumBuy").toString());
        }
        if (sure_sell != null) {
            from_buy_order_money -= Float.parseFloat(sure_sell.get("sureSellAmount").toString());
        }
        jsonObject.put("getAllAmount", from_buy_order_money);
        return CommonUtil.successJson(jsonObject);
    }


    @PostMapping("/update")
    public JSONObject updateBuyOrder(@RequestBody JSONObject requestJson) {
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
