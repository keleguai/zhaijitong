package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.util.Jwt;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/sell_order")
public class SellOrderController {

    @Autowired
    SellOrderService sellOrderService;


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
        PageInfo pageInfo = new PageInfo(sellOrderService.selectByUserId(userId));
        return CommonUtil.successJson(pageInfo);
    }


    @PostMapping("/add")
    public JSONObject addSellOrder(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        SellOrder sellOrder = JSONObject.toJavaObject(requestJson, SellOrder.class);
        sellOrderService.save(sellOrder);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateSellOrder(@RequestBody JSONObject requestJson) {
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
