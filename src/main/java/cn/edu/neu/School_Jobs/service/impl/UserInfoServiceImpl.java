package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.UserInfoMapper;
import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

}
