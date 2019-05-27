package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

public interface UserInfoMapper extends MyMapper<UserInfo> {
    int selectByIdAndPayPassword(JSONObject jsonObject);
}
