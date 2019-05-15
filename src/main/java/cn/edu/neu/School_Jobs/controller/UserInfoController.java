package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
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
@RequestMapping("/user_info")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findUserInfo(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = userInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }


    @GetMapping("/me")
    public JSONObject getMe(HttpServletRequest request) {
        int uid = Jwt.getUserId(request);
        UserInfo userInfo = userInfoService.findById(uid);
        if (userInfo != null) {
            userInfo.setPayPassword("");
            return CommonUtil.successJsonWithToken(userInfo, Jwt.updateToken(request));
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_779);
        }

    }

    @PostMapping("/add")
    public JSONObject addUserInfo(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        UserInfo userInfo = JSONObject.toJavaObject(requestJson, UserInfo.class);
        userInfo.setUserId(Jwt.getUserId(request));
        userInfo.setPhotoUrl(Jwt.getUserId(request) + ".jpg");
        userInfoService.save(userInfo);
        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateUserInfo(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        UserInfo userInfo = JSONObject.toJavaObject(requestJson, UserInfo.class);
        userInfo.setUserId(Jwt.getUserId(request));
        userInfoService.update(userInfo);
        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteUserInfo(@PathVariable(value = "id") int id) {

        userInfoService.deleteById(id);

        return CommonUtil.successJson();
    }


}
