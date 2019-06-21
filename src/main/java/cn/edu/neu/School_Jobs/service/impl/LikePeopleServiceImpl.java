package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.LikePeopleMapper;
import cn.edu.neu.School_Jobs.model.LikePeople;
import cn.edu.neu.School_Jobs.service.LikePeopleService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.LikePeopleJoinUserInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zzc on 2019/06/19.
 */
@Service
public class LikePeopleServiceImpl extends AbstractService<LikePeople> implements LikePeopleService {

    @Autowired
    private LikePeopleMapper likePeopleMapper;


}
