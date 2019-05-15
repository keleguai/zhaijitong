package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.User;
import cn.edu.neu.School_Jobs.util.Service;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface UserService extends Service<User> {
    int selectByPhone(String phone);

    List<User> selectAllByPhone(String phone);

    List<User> selectAllByPhoneAndPsw(String phone, String password);
}
