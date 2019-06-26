package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.LikePeople;
import cn.edu.neu.School_Jobs.service.LikePeopleService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
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

/**
 * Created by fzb on 2019/06/19.
 */
@RestController
@RequestMapping("/like_people")
public class LikePeopleController {

    @Autowired
    LikePeopleService likePeopleService;
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findLikePeople(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<LikePeople> list = likePeopleService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/get/me")
    public JSONObject getLikeMeFriend(HttpServletRequest request) {
        String userId = String.valueOf(Jwt.getUserId(request));
        return CommonUtil.successJson(userInfoService.findLikePeople(userId));
    }

    @PostMapping("/add")
    public JSONObject addLikePeople(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        LikePeople likePeople = JSONObject.toJavaObject(requestJson, LikePeople.class);
        likePeopleService.save(likePeople);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateLikePeople(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        LikePeople likePeople = JSONObject.toJavaObject(requestJson, LikePeople.class);
        likePeopleService.update(likePeople);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteLikePeople(@PathVariable(value = "id") int id) {

        likePeopleService.deleteById(id);

        return CommonUtil.successJson();
    }


}
