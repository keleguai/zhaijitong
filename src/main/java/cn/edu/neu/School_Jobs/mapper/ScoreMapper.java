package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.Score;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ScoreMapper extends MyMapper<Score> {
    int selectCountByField(JSONObject jsonObject);
    List<FundInfoJoinGradeVo> findByGrade(JSONObject jsonObject);
}
