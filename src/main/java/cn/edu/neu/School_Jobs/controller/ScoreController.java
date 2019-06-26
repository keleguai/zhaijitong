package cn.edu.neu.School_Jobs.controller;
import cn.edu.neu.School_Jobs.model.Score;
import cn.edu.neu.School_Jobs.service.ScoreService;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
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
 * Created by fzb on 2019/06/13.
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}/{desc}",method = RequestMethod.GET)
    public JSONObject findScore(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize,@PathVariable(value = "desc")int desc) {

    PageHelper.startPage(pageNum, pageSize);
    List<FundInfoJoinGradeVo> list = scoreService.findByGrade(desc==1?"true":null);
    PageInfo pageInfo = new PageInfo(list);
    return CommonUtil.successJson(pageInfo);
    }

    @PostMapping("/add")
    public JSONObject addScore(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        //try{
            //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
          //  return e.getResultJson();
        //}
        int userId = Jwt.getUserId(request);
        Score score = JSONObject.toJavaObject(requestJson,Score.class);
        score.setUserId(userId);
        score.setInsertTime(new Date());
        if(scoreService.selectCountByField(String.valueOf(userId),score.getFundId())==0){
            scoreService.save(score);
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_789);
    }

    @GetMapping("/has/count")
    public JSONObject hasCount(HttpServletRequest request){
        int userId = Jwt.getUserId(request);
        String fundId = request.getParameter("fundId");
        return CommonUtil.successJson(scoreService.selectCountByField(String.valueOf(userId),fundId));
    }

    @PostMapping("/update")
    public JSONObject updateScore(@RequestBody JSONObject requestJson) {
        //try{
          //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        Score score = JSONObject.toJavaObject(requestJson,Score.class);
        scoreService.update(score);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteScore(@PathVariable(value = "id") int id) {

        scoreService.deleteById(id);

        return CommonUtil.successJson();
    }


}
