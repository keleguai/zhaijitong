package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.Recommend;
import cn.edu.neu.School_Jobs.service.RecommendService;
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
 * Created by fzb on 2019/06/18.
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    RecommendService recommendService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findRecommend(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Recommend> list = recommendService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addRecommend(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        Recommend recommend = JSONObject.toJavaObject(requestJson, Recommend.class);
        recommendService.save(recommend);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateRecommend(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Recommend recommend = JSONObject.toJavaObject(requestJson, Recommend.class);
        recommendService.update(recommend);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteRecommend(@PathVariable(value = "id") int id) {

        recommendService.deleteById(id);

        return CommonUtil.successJson();
    }


}
