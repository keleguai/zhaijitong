package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.RecommendFund;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import cn.edu.neu.School_Jobs.vo.RecommendWIthFundVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface RecommendFundMapper extends MyMapper<RecommendFund> {
    List<RecommendWIthFundVo> findByGrade(JSONObject jsonObject);
}
