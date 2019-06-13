package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.ScoreMapper;
import cn.edu.neu.School_Jobs.model.Score;
import cn.edu.neu.School_Jobs.service.ScoreService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * Created by zzc on 2019/06/13.
 */
@Service
public class ScoreServiceImpl extends AbstractService<Score> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int selectCountByField(String userId,String fundId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("fundId",fundId);
        return scoreMapper.selectCountByField(jsonObject);
    }
    @Override
    public List<FundInfoJoinGradeVo> findByGrade(String desc){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("desc",desc);
        return scoreMapper.findByGrade(jsonObject);
    }
}
