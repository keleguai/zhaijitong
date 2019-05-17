package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.util.Service;

/**
 * created by fzb on 2019/05/10.
 */
public interface UserInfoService extends Service<UserInfo> {
    float getHistoryRate(int userId, int day);

    String getEncryPhotoUrl(int userId);
}
