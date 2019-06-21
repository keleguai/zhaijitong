package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.RecommendFundMapper;
import cn.edu.neu.School_Jobs.model.RecommendFund;
import cn.edu.neu.School_Jobs.service.RecommendFundService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import cn.edu.neu.School_Jobs.vo.RecommendWIthFundVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zzc on 2019/06/20.
 */
@Service
public class RecommendFundServiceImpl extends AbstractService<RecommendFund> implements RecommendFundService {

    @Autowired
    private RecommendFundMapper recommendFundMapper;

    @Override
    public List<RecommendWIthFundVo> findByGrade(String userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        return recommendFundMapper.findByGrade(jsonObject);
    }

}
