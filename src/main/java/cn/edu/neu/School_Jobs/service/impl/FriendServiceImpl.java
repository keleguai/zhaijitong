package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FriendMapper;
import cn.edu.neu.School_Jobs.model.Friend;
import cn.edu.neu.School_Jobs.service.FriendService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by zzc on 2019/06/13.
 */
@Service
public class FriendServiceImpl extends AbstractService<Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

}
