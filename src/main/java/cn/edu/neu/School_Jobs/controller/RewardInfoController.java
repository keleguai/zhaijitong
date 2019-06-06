package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.RewardInfo;
import cn.edu.neu.School_Jobs.service.RewardInfoService;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by fzb on 2019/06/02.
 */
@RestController
@RequestMapping("/reward_info")
public class RewardInfoController {

    @Autowired
    RewardInfoService rewardInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public JSONObject findRewardInfo(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

    PageHelper.startPage(pageNum, pageSize);
    List<RewardInfo> list = rewardInfoService.findAll();
    PageInfo pageInfo = new PageInfo(list);

    return CommonUtil.successJson(pageInfo);
    }

    @GetMapping("/get_last")
    public JSONObject getLastNews(){
        List<RewardInfo> list = rewardInfoService.findAll();
        return CommonUtil.successJson(list.get(list.size()-1));
    }
    @PostMapping("/add")
    public JSONObject addRewardInfo(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
            //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
          //  return e.getResultJson();
        //}
        if(Jwt.getUserType(request)!=1){
            return CommonUtil.errorJson(ErrorEnum.E_785);
        }
        RewardInfo rewardInfo = JSONObject.toJavaObject(requestJson, RewardInfo.class);
        rewardInfo.setUserId(Jwt.getUserId(request));
        rewardInfo.setInsertTime(new Date());
        rewardInfoService.save(rewardInfo);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateRewardInfo(@RequestBody JSONObject requestJson) {
        //try{
          //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        RewardInfo rewardInfo = JSONObject.toJavaObject(requestJson, RewardInfo.class);
        rewardInfoService.update(rewardInfo);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteRewardInfo(@PathVariable(value = "id") int id) {

        rewardInfoService.deleteById(id);

        return CommonUtil.successJson();
    }


}
