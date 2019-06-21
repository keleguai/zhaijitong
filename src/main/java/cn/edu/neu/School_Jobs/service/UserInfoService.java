package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.util.constants.ErrorEnum;
import cn.edu.neu.School_Jobs.vo.LikePeopleJoinUserInfoVo;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface UserInfoService extends Service<UserInfo> {
    float getHistoryRate(int userId, int day);

    String getEncryPhotoUrl(int userId);
    String getEncryPayPassword(String payPasswor);
    int selectByIdAndPayPassword(String userId,String payPassword);
    int computeAge(String IdNO);
    String computeSex(String IdNo);
    UserInfo anonymousUserInfo(UserInfo userInfo);

    List<UserInfo> findFriendByUserId(String userId);

    List<UserInfo> findIsAddingFriend(String userId);

    Boolean lockPayPassword(int uid);

    ErrorEnum addLockPayPassword(int uid);

    List<LikePeopleJoinUserInfoVo> findLikePeople(String userId);

}
