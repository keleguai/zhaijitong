package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface FriendMapper extends MyMapper<Friend> {
    int findHasSend(JSONObject jsonObject);

    List<Friend> findReadyAgreeFriend(JSONObject jsonObject);

    int deleteFriendByUserIdAndFriendId(JSONObject jsonObject);

    int findIsFriend(JSONObject jsonObject);
}
