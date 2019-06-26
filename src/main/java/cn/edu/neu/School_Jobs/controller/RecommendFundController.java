package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.RecommendFund;
import cn.edu.neu.School_Jobs.service.RecommendFundService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import cn.edu.neu.School_Jobs.vo.RecommendWIthFundVo;
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

/**
 * Created by fzb on 2019/06/20.
 */
@RestController
@RequestMapping("/recommend_fund")
public class RecommendFundController {

    @Autowired
    RecommendFundService recommendFundService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findRecommendFund(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<RecommendFund> list = recommendFundService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/get/me")
    public JSONObject findSelfRecommendFund(HttpServletRequest request) {
        PageHelper.startPage(1, 5);
        int userId = Jwt.getUserId(request);
        List<RecommendWIthFundVo> list = recommendFundService.findByGrade(String.valueOf(userId));
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addRecommendFund(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        RecommendFund recommendFund = JSONObject.toJavaObject(requestJson, RecommendFund.class);
        recommendFundService.save(recommendFund);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateRecommendFund(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        RecommendFund recommendFund = JSONObject.toJavaObject(requestJson, RecommendFund.class);
        recommendFundService.update(recommendFund);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteRecommendFund(@PathVariable(value = "id") int id) {

        recommendFundService.deleteById(id);

        return CommonUtil.successJson();
    }


}
