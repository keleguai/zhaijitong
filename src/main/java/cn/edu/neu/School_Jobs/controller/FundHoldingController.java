package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.FundHolding;
import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.service.FundHoldingService;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/fund_holding")
public class FundHoldingController {

    @Autowired
    FundHoldingService fundHoldingService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findFundHolding(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<FundHolding> list = fundHoldingService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/{fundId}", method = RequestMethod.GET)
    public JSONObject findHoldingStock(@PathVariable(value = "fundId") String fundId) {
        List<Stock> list = fundHoldingService.showStocks(fundId);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        FundHolding fundHolding = fundHoldingService.findById(fundId);
        String[] worth_percent = fundHolding.getWorthPercent().split("-");
        for (int i = 0; i < worth_percent.length; i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            o.put("worthPercent", worth_percent[i]);
            jsonArray.set(i, o);
        }
        return CommonUtil.successJson(jsonArray);
    }

    @PostMapping("/add")
    public JSONObject addFundHolding(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        FundHolding fundHolding = JSONObject.toJavaObject(requestJson, FundHolding.class);
        fundHoldingService.save(fundHolding);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFundHolding(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        FundHolding fundHolding = JSONObject.toJavaObject(requestJson, FundHolding.class);
        fundHoldingService.update(fundHolding);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFundHolding(@PathVariable(value = "id") int id) {

        fundHoldingService.deleteById(id);

        return CommonUtil.successJson();
    }


}
