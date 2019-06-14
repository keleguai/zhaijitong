package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FriendMapper;
import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.FriendService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * Created by zzc on 2019/06/13.
 */
@Service
public class FriendServiceImpl extends AbstractService<Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public int findHasSend(String userId, String friendId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("friendId", friendId);
        return friendMapper.findHasSend(jsonObject);
    }

    @Override
    public List<Friend> findReadyAgreeFriend(String userId, String friendId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("friendId", friendId);
        return friendMapper.findReadyAgreeFriend(jsonObject);
    }

    @Override
    public int deleteFriendByUserIdAndFriendId(String userId, String friendId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("friendId", friendId);
        return friendMapper.deleteFriendByUserIdAndFriendId(jsonObject);
    }
}
