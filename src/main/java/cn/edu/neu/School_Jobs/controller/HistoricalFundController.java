package cn.edu.neu.School_Jobs.controller;

import cn.edu.neu.School_Jobs.model.HistoricalFund;
import cn.edu.neu.School_Jobs.service.HistoricalFundService;
import cn.edu.neu.School_Jobs.vo.HistoryFundJoinFundVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.neu.School_Jobs.util.CommonUtil;

/**
 * created by fzb on 2019/05/10.
 */
@RestController
@RequestMapping("/historical_fund")
public class HistoricalFundController {

    @Autowired
    HistoricalFundService historicalFundService;


    @RequestMapping(value = "/list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public JSONObject findHistoricalFund(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "pageSize") int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HistoricalFund> list = historicalFundService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        return CommonUtil.successJson(pageInfo);
    }

    //todo 展示排行表
    @RequestMapping(value = "/rank/{day}/{pageNum}/{desc}", method = RequestMethod.GET)
    public JSONObject getRank(@PathVariable(value = "pageNum") int pageNum, @PathVariable(value = "day") int day,@PathVariable(value = "desc")Boolean desc) {
        List<HistoryFundJoinFundVo> historyFundJoinFundVos = historicalFundService.findFundWithHistoryData();
        // 设置1页的数量
        int pageSize = 5;
        List<HistoryFundJoinFundVo> list = new ArrayList<>();
        int count = historyFundJoinFundVos.size();
        int loop = Math.min(pageNum * pageSize, historyFundJoinFundVos.size());
        for (int i = 0; i < loop; i++) {
            // 设置最小值更新以及下标
            Float min = Float.MIN_VALUE;
            Float max = Float.MAX_VALUE;
            int index = 0;
            for (int j = 0; j < historyFundJoinFundVos.size(); j++) {
                String[] prices = historyFundJoinFundVos.get(j).getHistoryPrice().split("-");
                int length = prices.length;
                day = length - 1 > day ? day : length - 1;
                // 价格变化率
                float rate = Float.parseFloat(prices[length - 1]) / Float.parseFloat(prices[length - 1 - day]);
                if(desc){
                    if (rate > min) {
                        // 更新最小值和下标
                        min = rate;
                        index = j;
                    }
                }else {
                    if(rate < max){
                        max = rate;
                        index = j;
                    }
                }
            }
            HistoryFundJoinFundVo max_history = historyFundJoinFundVos.get(index);
            if(desc){
                max_history.setHistoryRate(min.toString());
            }else {
                max_history.setHistoryRate(max.toString());
            }
            max_history.setDate(null);
            String[] prices = max_history.getHistoryPrice().split("-");
            max_history.setHistoryPrice(prices[prices.length-1]);
            list.add(historyFundJoinFundVos.get(index));
            // 弹出最大或者最小元素
            historyFundJoinFundVos.remove(index);
        }
        return CommonUtil.successJson(CommonUtil.pageInfo(pageNum, pageSize, count, list));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONObject findHistoryId(@PathVariable(value = "id") String id) {
        return CommonUtil.successJson(historicalFundService.findById(id));
    }


    @PostMapping("/add")
    public JSONObject addHistoricalFund(@RequestBody JSONObject requestJson) {
        //try{
        //CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //  return e.getResultJson();
        //}

        HistoricalFund historicalFund = JSONObject.toJavaObject(requestJson, HistoricalFund.class);
        historicalFundService.save(historicalFund);

        return CommonUtil.successJson();
    }

    @PostMapping("/update")
    public JSONObject updateHistoricalFund(@RequestBody JSONObject requestJson) {
        //try{
        //  CommonUtil.hasAllRequired(requestJson, "name,description");
        //}catch (CommonJsonException e){
        //    return e.getResultJson();
        //}

        HistoricalFund historicalFund = JSONObject.toJavaObject(requestJson, HistoricalFund.class);
        historicalFundService.update(historicalFund);

        return CommonUtil.successJson();
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject deleteHistoricalFund(@PathVariable(value = "id") int id) {

        historicalFundService.deleteById(id);

        return CommonUtil.successJson();
    }


}
