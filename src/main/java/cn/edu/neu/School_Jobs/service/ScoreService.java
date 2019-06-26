package cn.edu.neu.School_Jobs.service;
import cn.edu.neu.School_Jobs.model.Score;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.FundInfoJoinGradeVo;

import java.util.List;

/**
 *
 * Created by fzb on 2019/06/13.
 */
public interface ScoreService extends Service<Score> {
    int selectCountByField(String userId,String fundId);
    List<FundInfoJoinGradeVo> findByGrade(String desc);
}
