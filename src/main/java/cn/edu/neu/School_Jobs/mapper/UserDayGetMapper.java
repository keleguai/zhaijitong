package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.UserDayGet;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.UserDayGetJoinFundVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserDayGetMapper extends MyMapper<UserDayGet> {
    List<UserDayGetJoinFundVo> findByTimeAgo(JSONObject jsonObject);
}
