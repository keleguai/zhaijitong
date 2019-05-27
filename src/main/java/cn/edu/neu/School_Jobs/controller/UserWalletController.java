package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.UserWallet;
import cn.edu.neu.School_Jobs.service.UserWalletService;
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
@RequestMapping("/user_wallet")
public class UserWalletController {

    @Autowired
    UserWalletService userWalletService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findUserWallet(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserWallet> list = userWalletService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addUserWallet(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        UserWallet userWallet = JSONObject.toJavaObject(requestJson, UserWallet.class);
        userWalletService.save(userWallet);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateUserWallet(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        UserWallet userWallet = JSONObject.toJavaObject(requestJson, UserWallet.class);
        userWalletService.update(userWallet);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteUserWallet(@PathVariable(value = "id") int id) {

        userWalletService.deleteById(id);

        return CommonUtil.successJson();
    }
}
