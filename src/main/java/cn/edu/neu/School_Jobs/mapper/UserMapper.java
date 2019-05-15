package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.User;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    int selectByPhone(JSONObject jsonObject);

    List<User> selectAllByPhone(JSONObject jsonObject);

    List<User> selectAllByPhoneAndPsw(JSONObject jsonObject);
}
