package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.*;
import cn.edu.neu.School_Jobs.util.CommonUtil;
import cn.edu.neu.School_Jobs.util.Jwt;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    FundService fundService;
    @Autowired
    UserService userService;
    @Autowired
    BuyOrderService buyOrderService;
    @Autowired
    HistoricalFundService historicalFundService;
    @Autowired
    SellOrderService sellOrderService;
    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findFund(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Fund> list = fundService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }


    //仅展示前十名
    @RequestMapping(value = "/rank/user/{day}", method = RequestMethod.GET)
    public JSONObject showUser(@PathVariable(value = "day") int day) {
        //找出所有用户
        List<UserInfo> users = userInfoService.findAll();
        // 建立一个ARRAY映射每个客户
        JSONArray jsonArray = new JSONArray();
        //  建立一个LIST 填入前十名用户信息
        List<JSONObject> lists = new ArrayList<>();
        // 遍历每个用户
        for (UserInfo user : users) {
            // 如果客户不愿意展示自己的排名,就跳过这位老哥
            if (!user.getShowMe()) {
                continue;
            }
            // 得到用户id
            int userId = user.getUserId();
            // 添加用户的信息
            JSONObject userInfoWithRate = new JSONObject();
            Float rate = userInfoService.getHistoryRate(userId, day);
            // 放入客户的收益率以及客户信息
            userInfoWithRate.put("getRate", rate);
            userInfoWithRate.put("userInfo", userInfoService.findById(userId));
            // 放入Array中
            jsonArray.add(userInfoWithRate);
        }
        // 由于我们仅仅取前十名，故设置loop为10，但是不能超过用户信息的最大长度
        int loop = Math.min(10, jsonArray.size());
        // 开始循环十次弹出最大的用户
        for (int i = 0; i < loop; i++) {
            // 设置最小值 贪心算法
            float min = Float.MIN_VALUE;
            // 设置返回的最大值下标
            int index = 0;
            // 循环用户信息判断最大的用户
            for (int j = 0; j < jsonArray.size(); j++) {
                // 得到json的信息
                JSONObject userInfoWithRate = (JSONObject) (jsonArray.get(j));
                // 得到这位用户的收益率
                float thisUserRate = Float.parseFloat(userInfoWithRate.get("getRate").toString());
                //如果最小值小于RATE的话，更新下标和最小值
                if (min <= thisUserRate) {
                    index = j;
                    min = thisUserRate;
                }
            }
            // 循环完后取出最大值并移除JSONARYY的值
            lists.add((JSONObject) jsonArray.get(index));
            jsonArray.remove(index);
        }
        return CommonUtil.successJson(lists);
    }

    // 通过id取得基金信息
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject fund_id(@PathVariable(value = "id") String id) {
        return CommonUtil.successJson(fundService.findById(id));
    }

    @RequestMapping(value = "/dis/{pageNum}", method = RequestMethod.GET)
    public JSONObject allDisFund(@PathVariable(value = "pageNum") int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByDisStock();
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/by/{stockId}", method = RequestMethod.GET)
    public JSONObject DisFundId(@PathVariable(value = "stockId") String stockId) {
        List<Fund> list = fundService.selectByStockId(stockId);
        return CommonUtil.successJson(list);
    }


    @RequestMapping(value = "/type/{pageNum}/{type}", method = RequestMethod.GET)
    public JSONObject fund_type(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "type") String type) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByField(type,null);
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/regex/{pageNum}", method = RequestMethod.GET)
    public JSONObject fund_regex(@PathVariable(value = "pageNum") int pageNum, HttpServletRequest request) {
        PageHelper.startPage(pageNum, 5);
        List<Fund> list = fundService.selectByAll(request.getParameter("regex"));
        PageInfo pageInfo = new PageInfo(list);
        return CommonUtil.successJson(pageInfo);
    }


    @RequestMapping(value = "/priority", method = RequestMethod.GET)
    public JSONObject priorityFund() {
        List<Fund> list = fundService.selectOrderByPriority();
        return CommonUtil.successJson(list);
    }

    @PostMapping("/add")
    public JSONObject addFund(@RequestBody JSONObject requestJson) {
        Fund fund = JSONObject.toJavaObject(requestJson, Fund.class);
        fundService.save(fund);
        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateFund(@RequestBody JSONObject requestJson,HttpServletRequest request) {
        int userId = Jwt.getUserId(request);
        Fund fund = JSONObject.toJavaObject(requestJson, Fund.class);
        // 对于更新基金的行为做一下验证，验证是否是管理员
        if(userService.findById(userId).getUserType()==1){
            fundService.update(fund);
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson(ErrorEnum.E_785);
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteFund(@PathVariable(value = "id") int id) {

        fundService.deleteById(id);

        return CommonUtil.successJson();
    }


}
