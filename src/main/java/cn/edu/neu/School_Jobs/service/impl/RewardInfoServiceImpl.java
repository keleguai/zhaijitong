package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.RewardInfoMapper;
import cn.edu.neu.School_Jobs.model.RewardInfo;
import cn.edu.neu.School_Jobs.service.RewardInfoService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by fzb on 2019/06/02.
 */
@Service
public class RewardInfoServiceImpl extends AbstractService<RewardInfo> implements RewardInfoService {

    @Autowired
    private RewardInfoMapper rewardInfoMapper;

}
