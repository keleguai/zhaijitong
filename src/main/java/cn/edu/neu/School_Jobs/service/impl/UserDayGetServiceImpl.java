package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.UserDayGetMapper;
import cn.edu.neu.School_Jobs.model.UserDayGet;
import cn.edu.neu.School_Jobs.service.UserDayGetService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.UserDayGetJoinFundVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by fzb on 2019/06/25.
 */
@Service
public class UserDayGetServiceImpl extends AbstractService<UserDayGet> implements UserDayGetService {

    @Autowired
    private UserDayGetMapper userDayGetMapper;

    @Override
    public List<UserDayGetJoinFundVo> findByTimeAgo(String userId, String day, String theTime) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("day", day);
        jsonObject.put("theTime", theTime);
        return userDayGetMapper.findByTimeAgo(jsonObject);
    }
}
