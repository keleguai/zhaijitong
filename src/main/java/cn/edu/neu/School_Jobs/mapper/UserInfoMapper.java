package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.LikePeopleJoinUserInfoVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserInfoMapper extends MyMapper<UserInfo> {
    int selectByIdAndPayPassword(JSONObject jsonObject);

    List<UserInfo> findFriendByUserId(JSONObject jsonObject);

    List<UserInfo> findIsAddingFriend(JSONObject jsonObject);

    List<LikePeopleJoinUserInfoVo> findLikePeople(String userId);
}
