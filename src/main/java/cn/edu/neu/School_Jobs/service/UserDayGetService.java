package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.UserDayGet;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.UserDayGetJoinFundVo;

import java.util.List;

/**
 * Created by fzb on 2019/06/25.
 */
public interface UserDayGetService extends Service<UserDayGet> {
    List<UserDayGetJoinFundVo> findByTimeAgo(String userId, String day, String theTime);
}
