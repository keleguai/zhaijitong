package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

import javax.servlet.http.HttpServletRequest;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/sell_order")
public class SellOrderController {

    @Autowired
    SellOrderService sellOrderService;
    @Autowired
    BuyOrderService buyOrderService;
    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findSellOrder(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<SellOrder> list = sellOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/show_sell/{page}")
    public JSONObject show_buy(HttpServletRequest request, @PathVariable(value = "page") int page) {
        int userId = Jwt.getUserId(request);
        PageHelper.startPage(page, 5);
        PageInfo pageInfo = new PageInfo(sellOrderService.findOrdersWithFundInfo(userId));
        return CommonUtil.successJson(pageInfo);
    }


    /**
     * @author fzb
     * @date 2019/5/30
     * @description:不对已有买入订单进行直接操作了
     */
    @PostMapping("/add")
    public JSONObject addSellOrder(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        // 得到用户id
        int userId = Jwt.getUserId(request);
        String payPassword =userInfoService.getEncryPayPassword(requestJson.get("payPassword").toString());
        if(userInfoService.selectByIdAndPayPassword(String.valueOf(userId),payPassword)==0){
            return CommonUtil.errorJson(ErrorEnum.E_784);
        }
        try{
            // 如果强制转换失败，说明卖出的值非数字
            SellOrder sellOrder = JSONObject.toJavaObject(requestJson, SellOrder.class);
            sellOrder.setUserId(userId);
            // 得到所有已经该客户已经确认买入的这个基金订单
            List<BuyOrder> buyOrders = buyOrderService.findOneFundOrders(userId, sellOrder.getFundId());
            // 判断卖的值是否正确，所以先拿到卖的份额
            Float remainShare = sellOrder.getSellShare();
            if(remainShare<=1){
                return CommonUtil.errorJson(ErrorEnum.E_787);
            }
            // 找出所有卖出未确认的基金，再对这部分份额进行操作
            List<SellOrder> sellOrders =  sellOrderService.findHistoryOrder(365,userId,"0",sellOrder.getFundId());
            float willSellShare = 0;
            for(SellOrder willSellOrder:sellOrders){
                willSellShare += willSellOrder.getSellShare();
            }
//            System.out.println(remainShare+"这是剩余份额");
//            System.out.println(willSellShare+"这是总份额");
            remainShare += willSellShare;
            for (int i = 0; i < buyOrders.size(); i++) {
                BuyOrder aBuyOrder = buyOrders.get(i);
                // 如果订单的份额大于买单的剩余份额
                if (remainShare >= aBuyOrder.getResidualShare()) {
                    // 那么减去这个买入的订单所剩余的所有份额
                    remainShare -= aBuyOrder.getResidualShare();
                    // 将这个买入的订单的剩余份额更新为0
                    aBuyOrder.setResidualShare(0.f);
                } else {
                    // 将买入的订单减去要卖的份额，并将卖出份额置为0，同时中止循环，不对其他的买单进行操作
                    aBuyOrder.setResidualShare(aBuyOrder.getResidualShare() - remainShare);
                    remainShare = 0.f;
                    break;
                }
            }

            // 误差设置为0.1，如果一个循环下去，卖出订单的份额还大于0.1的话，则显示买卖失败
            if (remainShare > 0.1) {
                return CommonUtil.errorJson(ErrorEnum.E_782);
            } else {
                // 更新所有买的订单表
//                for (BuyOrder buyOrder : buyOrders) {
//                    buyOrderService.update(buyOrder);
//                }
                // 初始化卖单信息以方便插入
                sellOrder = sellOrderService.initialSellOrder(sellOrder,userId);
                sellOrderService.save(sellOrder);
                return CommonUtil.successJson();
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return CommonUtil.errorJson(ErrorEnum.E_783);
        }
    }

    @PostMapping("/update")
    public JSONObject updateSellOrder(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        SellOrder sellOrder = JSONObject.toJavaObject(requestJson, SellOrder.class);

        sellOrderService.update(sellOrder);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteSellOrder(@PathVariable(value = "id") int id) {

        sellOrderService.deleteById(id);

        return CommonUtil.successJson();
    }


}
