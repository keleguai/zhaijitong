package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.RecommendFund;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import cn.edu.neu.School_Jobs.vo.RecommendWIthFundVo;

import java.util.List;

/**
 * Created by zzc on 2019/06/20.
 */
public interface RecommendFundService extends Service<RecommendFund> {
    List<RecommendWIthFundVo> findByGrade(String userId);
}
