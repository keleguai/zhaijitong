package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.UserMapper;
import cn.edu.neu.School_Jobs.model.User;
import cn.edu.neu.School_Jobs.service.UserService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int selectByPhone(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        return userMapper.selectByPhone(jsonObject);
    }

    @Override
    public List<User> selectAllByPhone(String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        return userMapper.selectAllByPhone(jsonObject);
    }

    @Override
    public List<User> selectAllByPhoneAndPsw(String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", phone);
        jsonObject.put("password", password);
        return userMapper.selectAllByPhoneAndPsw(jsonObject);
    }

}
