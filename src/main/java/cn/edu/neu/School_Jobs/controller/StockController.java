package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.service.StockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findStock(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Stock> list = stockService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    // 停牌表
    @RequestMapping(value = "/disappear/{pageNum}", method = RequestMethod.GET)
    public JSONObject findStockDisappear(@PathVariable(value = "pageNum") int pageNum) {

        PageHelper.startPage(pageNum, 5);
        List<Stock> list = stockService.selectByDisappear();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addStock(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        Stock stock = JSONObject.toJavaObject(requestJson, Stock.class);
        stockService.save(stock);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateStock(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Stock stock = JSONObject.toJavaObject(requestJson, Stock.class);
        stockService.update(stock);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteStock(@PathVariable(value = "id") int id) {

        stockService.deleteById(id);

        return CommonUtil.successJson();
    }


}
