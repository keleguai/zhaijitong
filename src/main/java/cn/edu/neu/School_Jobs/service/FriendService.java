package cn.edu.neu.School_Jobs.service;
import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.util.Service;

import java.util.List;

/**
 *
 * Created by zzc on 2019/06/13.
 */
public interface FriendService extends Service<Friend> {
    int findHasSend(String userId, String friendId);

    List<Friend> findReadyAgreeFriend(String userId, String friendId);

    int deleteFriendByUserIdAndFriendId(String userId, String friendId);

    int findIsFriend(String userId, String friendId);
}
