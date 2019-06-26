package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.UserDayGet;
import cn.edu.neu.School_Jobs.service.UserDayGetService;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.util.Jwt;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fzb on 2019/06/25.
 */
@RestController
@RequestMapping("/user_day_get")
public class UserDayGetController {

    @Autowired
    UserDayGetService userDayGetService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findUserDayGet(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserDayGet> list = userDayGetService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/get/me")
    public JSONObject getMyDayGer(HttpServletRequest request, @RequestBody JSONObject requestJson) {
        int userId = Jwt.getUserId(request);
        String day = requestJson.getString("day");
        String theTime = requestJson.getString("theTime");
        return CommonUtil.successJson(userDayGetService.findByTimeAgo(String.valueOf(userId), day, theTime));
    }

    @PostMapping("/add")
    public JSONObject addUserDayGet(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        UserDayGet userDayGet = JSONObject.toJavaObject(requestJson, UserDayGet.class);
        userDayGetService.save(userDayGet);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateUserDayGet(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        UserDayGet userDayGet = JSONObject.toJavaObject(requestJson, UserDayGet.class);
        userDayGetService.update(userDayGet);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteUserDayGet(@PathVariable(value = "id") int id) {

        userDayGetService.deleteById(id);

        return CommonUtil.successJson();
    }


}
