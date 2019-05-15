package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.UserWalletMapper;
import cn.edu.neu.School_Jobs.model.UserWallet;
import cn.edu.neu.School_Jobs.service.UserWalletService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class UserWalletServiceImpl extends AbstractService<UserWallet> implements UserWalletService {

    @Autowired
    private UserWalletMapper userWalletMapper;

}
