package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.service.FundService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    FundService fundService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findFund(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Fund> list = fundService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject fund_id(@PathVariable(value = "id") String id) {
        return CommonUtil.successJson(fundService.findById(id));
    }

    @RequestMapping(value = "/dis/{pageNum}", method = RequestMethod.GET)
    public JSONObject allDisFund(@PathVariable(value = "pageNum") int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByDisStock();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/disappear/{stockId}/{pageNum}", method = RequestMethod.GET)
    public JSONObject DisFundId(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "stockId") String stockId) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByDisStockId(stockId);
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }


    @RequestMapping(value = "/type/{pageNum}/{type}", method = RequestMethod.GET)
    public JSONObject fund_type(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "type") String type) {

        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByType(type);
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/regex/{pageNum}", method = RequestMethod.GET)
    public JSONObject fund_regex(@PathVariable(value = "pageNum") int pageNum, HttpServletRequest request) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByAll(request.getParameter("regex"));
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/priority", method = RequestMethod.GET)
    public JSONObject priorityFund() {
        List<Fund> list = fundService.selectOrderByPriority();
        return CommonUtil.successJson(list);
    }

    @PostMapping("/add")
    public JSONObject addFund(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        Fund fund = JSONObject.toJavaObject(requestJson, Fund.class);
        fundService.save(fund);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFund(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Fund fund = JSONObject.toJavaObject(requestJson, Fund.class);
        fundService.update(fund);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFund(@PathVariable(value = "id") int id) {

        fundService.deleteById(id);

        return CommonUtil.successJson();
    }


}
