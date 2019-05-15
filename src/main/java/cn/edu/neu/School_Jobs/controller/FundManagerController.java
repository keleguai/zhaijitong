package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.FundManager;
import cn.edu.neu.School_Jobs.service.FundManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;


@RestController
@RequestMapping("/fund_manager")
public class FundManagerController {

    @Autowired
    FundManagerService fundManagerService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findFundManager(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<FundManager> list = fundManagerService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addFundManager(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        FundManager fundManager = JSONObject.toJavaObject(requestJson, FundManager.class);
        fundManagerService.save(fundManager);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFundManager(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        FundManager fundManager = JSONObject.toJavaObject(requestJson, FundManager.class);
        fundManagerService.update(fundManager);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFundManager(@PathVariable(value = "id") int id) {

        fundManagerService.deleteById(id);

        return CommonUtil.successJson();
    }


}
