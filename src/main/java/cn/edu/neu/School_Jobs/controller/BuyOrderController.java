package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.service.FundService;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.BuyFundJoinVo;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findBuyOrder(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<BuyOrder> list = buyOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addBuyOrder(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        try {
            BuyOrder buyOrder = JSONObject.toJavaObject(requestJson, BuyOrder.class);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            buyOrder.setUserId(Jwt.getUserId(request));
            buyOrder.setTimeBuying(date);
            buyOrder.setConfirmSign(false);
            buyOrder.setConfirmTheNet(null);
            buyOrder.setResidualShare(-1.f);
            buyOrder.setTimeConfirm(null);
            //        计算手续费
            Fund fund = fundService.findById(buyOrder.getFundId());
            buyOrder.setServiceCharge(fund.getBuyingRate() * buyOrder.getTransactionAmount());
            if (buyOrder.getTransactionAmount() < 1) {
                return CommonUtil.errorJson(ErrorEnum.E_780);
            }
            buyOrderService.save(buyOrder);
            return CommonUtil.successJson();
        } catch (Exception e) {
            return CommonUtil.errorJson(ErrorEnum.E_781);
        }


    }

    @GetMapping("/show_buy/{page}")
    public JSONObject show_buy(HttpServletRequest request, @PathVariable(value = "page") int page) {
        int userId = Jwt.getUserId(request);
        PageHelper.startPage(page, 5);
        PageInfo pageInfo = new PageInfo(buyOrderService.selectByUserId(userId));
        return CommonUtil.successJson(pageInfo);
    }


    @GetMapping("/show_has_funds/{page}")
    public JSONObject show_has_funds(HttpServletRequest request, @PathVariable(value = "page") int page) {
        int userId = Jwt.getUserId(request);

        PageHelper.startPage(page, 5);

        // 字典 过滤相同的东西用的
        JSONArray jsonArray = new JSONArray();

        List<BuyFundJoinVo> buyOrders = buyOrderService.selectByUserId(userId);

        PageInfo pageInfo = new PageInfo();

        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/show/buy_amount")
    public JSONObject show_buy_amount(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        int userId = Jwt.getUserId(request);
        // 买入未确认所花费的钱
        HashMap not_sure_buy = buyOrderService.getSumByBuyMoney(userId);

        // 买入确认后剩余份额*净值-手续费的钱
        HashMap sure_buy = buyOrderService.getSumByNetMoney(userId);

        HashMap sure_sell = sellOrderService.selectHasSellMoney(userId);
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
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

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
