package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.RecommendMapper;
import cn.edu.neu.School_Jobs.model.Recommend;
import cn.edu.neu.School_Jobs.service.RecommendService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zzc on 2019/06/18.
 */
@Service
public class RecommendServiceImpl extends AbstractService<Recommend> implements RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;

}
