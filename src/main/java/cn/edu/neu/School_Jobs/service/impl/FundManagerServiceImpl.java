package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FundManagerMapper;
import cn.edu.neu.School_Jobs.model.FundManager;
import cn.edu.neu.School_Jobs.service.FundManagerService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class FundManagerServiceImpl extends AbstractService<FundManager> implements FundManagerService {

    @Autowired
    private FundManagerMapper fundManagerMapper;

}
