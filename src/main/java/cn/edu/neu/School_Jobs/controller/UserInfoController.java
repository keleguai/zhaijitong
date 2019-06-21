package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Date;
import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * created by fzb on 2019/05/10.
 */
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


    // 得到该客户的收益率
    @GetMapping("/me/get_rate")
    public JSONObject getRate(HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        // 将客户的收益率封装成字典
        JSONObject jsonObject = new JSONObject();
        Float rate = userInfoService.getHistoryRate(userId, 500);
        jsonObject.put("rate", rate);
        return CommonUtil.successJson(jsonObject);
    }

    // 得到个人信息
    @GetMapping("/me")
    public JSONObject getMe(HttpServletRequest request) {
        int uid = Jwt.getUserId(request);
        UserInfo userInfo = userInfoService.findById(uid);
        if (userInfo != null) {
            userInfo.setPayPassword("");
            userInfo.setPhotoUrl(userInfo.getPhotoUrl()+"?t=t"+new Date().getTime());
            userInfo = userInfoService.anonymousUserInfo(userInfo);
            return CommonUtil.successJsonWithToken(userInfo, Jwt.updateToken(request));
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_779);
        }

    }

    @PostMapping("/add")
    public JSONObject addUserInfo(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        UserInfo userInfo = JSONObject.toJavaObject(requestJson, UserInfo.class);
        int userId = Jwt.getUserId(request);
        // 加密图片地址
        String photoUrl = userInfoService.getEncryPhotoUrl(userId);
        String encryPayPassword = userInfoService.getEncryPayPassword(userInfo.getPayPassword());
        // 加密用户支付密码
        userInfo.setPayPassword(encryPayPassword);
        // 由身份证获取年龄
        userInfo.setAge(userInfoService.computeAge(userInfo.getIdentityCard()));
        // 由身份证获取性别
        userInfo.setSex(userInfoService.computeSex(userInfo.getIdentityCard()));
        userInfo.setUserId(userId);
        if(userInfo.getPhotoUrl()!=null){
            userInfo.setPhotoUrl("/static/" + photoUrl);
        }else{
            userInfo.setPhotoUrl("/static/default.jpg");
        }
        userInfoService.save(userInfo);
        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateUserInfo(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        String photoUrl = userInfoService.getEncryPhotoUrl(userId);
        UserInfo userInfo = JSONObject.toJavaObject(requestJson, UserInfo.class);
        if(userInfo.getPayPassword()!=null&&userInfo.getPayPassword()!=null){
            userInfo.setPayPassword(userInfoService.getEncryPayPassword(userInfo.getPayPassword()));
        }
        if(userInfo.getIdentityCard()!=null&&userInfo.getIdentityCard().length()==18){
            // 由身份证获取年龄
            userInfo.setAge(userInfoService.computeAge(userInfo.getIdentityCard()));
            // 由身份证获取性别
            userInfo.setSex(userInfoService.computeSex(userInfo.getIdentityCard()));
        }
        userInfo.setPhotoUrl("/static/" + photoUrl);
        userInfo.setUserId(Jwt.getUserId(request));
        userInfoService.update(userInfo);
        return CommonUtil.successJson();
    }

    // 上传头像
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject upload(HttpServletRequest request, @RequestParam("file") MultipartFile picture) throws IOException {
        int userId = Jwt.getUserId(request);
        String photoUrl = userInfoService.getEncryPhotoUrl(userId);
        File f = new File("C:/Users/cole/Downloads/nginx-1.16.0/html/static/user/" + photoUrl);
        BufferedOutputStream out = null;
        out = new BufferedOutputStream(new FileOutputStream(f));
        out.write(picture.getBytes());
        out.flush();
        out.close();
        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteUserInfo(@PathVariable(value = "id") int id) {
        userInfoService.deleteById(id);
        return CommonUtil.successJson();
    }


}
