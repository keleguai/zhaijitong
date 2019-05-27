package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.User;
import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.Constants;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

import javax.servlet.http.HttpServletRequest;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findUser(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject requestJson) {
        String phone = requestJson.getString("phone_number");
        String password = requestJson.getString("password");
        List<User> user = userService.selectAllByPhoneAndPsw(phone, Encryptor.encrypt(password, phone));
        if (!user.isEmpty()) {
            Map<String, Object> payload = new HashMap<String, Object>();
            Date date = new Date();
            payload.put("userId", user.get(0).getUserId());//用户ID
            payload.put("startTime", date.getTime());//生成时间
            payload.put("expiryTime", date.getTime() + Constants.EXPIRY_TIME);//过期时间1小时
            payload.put("type", user.get(0).getUserType());
            String token = Jwt.createToken(payload);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", user.get(0).getUserType());
            return CommonUtil.successJsonWithToken(jsonObject, token);
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_778);
        }
    }

    @PostMapping("/add")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        User user = JSONObject.toJavaObject(requestJson, User.class);
        if (userService.selectByPhone(requestJson.getString("phone_number")) == 0) {
            user.setUserType(0);
            user.setPassword(Encryptor.encrypt(user.getPassword(), user.getPhoneNumber()));
            userService.save(user);
            Map<String, Object> payload = new HashMap<String, Object>();
            List<User> thisUser = userService.selectAllByPhone(requestJson.getString("phone_number"));
            Date date = new Date();
            payload.put("userId", thisUser.get(0).getUserId());//用户ID
            payload.put("startTime", date.getTime());//生成时间
            payload.put("expiryTime", date.getTime() + Constants.EXPIRY_TIME);//过期时间1小时
            payload.put("type", user.getUserType());
            String token = Jwt.createToken(payload);
            return CommonUtil.successJsonWithToken(null, token);
        }
        return CommonUtil.errorJson(ErrorEnum.E_777);
    }

    @PostMapping("/update")
    public JSONObject updateUser(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        User user = JSONObject.toJavaObject(requestJson, User.class);
        List<User> thisUser = userService.selectAllByPhone(user.getPhoneNumber());
        if(thisUser==null){
            return CommonUtil.errorJson(ErrorEnum.E_778);
        }
        user.setUserId(thisUser.get(0).getUserId());
        user.setPassword(Encryptor.encrypt(user.getPassword(), user.getPhoneNumber()));
        userService.update(user);
        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteUser(@PathVariable(value = "id") int id) {

        userService.deleteById(id);

        return CommonUtil.successJson();
    }


}
