package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.FundValuation;
import cn.edu.neu.School_Jobs.service.FundValuationService;
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
@RequestMapping("/fund_valuation")
public class FundValuationController {

    @Autowired
    FundValuationService fundValuationService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findFundValuation(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<FundValuation> list = fundValuationService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject findHistoryId(@PathVariable(value = "id") String id) {
        return CommonUtil.successJson(fundValuationService.findById(id));
    }

    @PostMapping("/add")
    public JSONObject addFundValuation(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        FundValuation fundValuation = JSONObject.toJavaObject(requestJson, FundValuation.class);
        fundValuationService.save(fundValuation);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFundValuation(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        FundValuation fundValuation = JSONObject.toJavaObject(requestJson, FundValuation.class);
        fundValuationService.update(fundValuation);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFundValuation(@PathVariable(value = "id") int id) {

        fundValuationService.deleteById(id);

        return CommonUtil.successJson();
    }


}
