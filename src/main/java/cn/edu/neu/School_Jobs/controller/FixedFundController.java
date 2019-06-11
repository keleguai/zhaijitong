package cn.edu.neu.School_Jobs.controller;
import cn.edu.neu.School_Jobs.model.FixedFund;
import cn.edu.neu.School_Jobs.service.FixedFundService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.FixedFundJoinFundVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.conf.exception.CommonJsonException;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by zzc on 2019/06/05.
 */
@RestController
@RequestMapping("/fixed_fund")
public class FixedFundController {

    @Autowired
    FixedFundService fixedFundService;
    UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JSONObject findFixedFund(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

    PageHelper.startPage(pageNum, pageSize);
    List<FixedFund> list = fixedFundService.findAll();
    PageInfo pageInfo = new PageInfo(list);

    return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/find/me/{page}")
    public JSONObject findMyself(HttpServletRequest request,@PathVariable(value = "page")int page){
        int userId = Jwt.getUserId(request);
        PageHelper.startPage(page, 5);
        List<FixedFundJoinFundVo> fixedFundJoinFundVos = fixedFundService.findByUserId(String.valueOf(userId));
        PageInfo pageInfo  = new PageInfo(fixedFundJoinFundVos);
        return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addFixedFund(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
            //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
          //  return e.getResultJson();
        //}
        int userId = Jwt.getUserId(request);

        FixedFund fixedFund = JSONObject.toJavaObject(requestJson,FixedFund.class);
        fixedFund.setId(null);
        fixedFund.setUserId(userId);
        fixedFund.setSendTime(new Date());
        fixedFund.setConfirmTime(null);
        fixedFundService.save(fixedFund);
        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFixedFund(@RequestBody JSONObject requestJson) {
        //try{
          //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        FixedFund fixedFund = JSONObject.toJavaObject(requestJson,FixedFund.class);
        fixedFundService.update(fixedFund);

        return CommonUtil.successJson();
    }

    @GetMapping("/delete/{id}")
    public JSONObject deleteFixedFund(@PathVariable(value = "id") int id) {


        fixedFundService.deleteById(id);

        return CommonUtil.successJson();
    }


}
