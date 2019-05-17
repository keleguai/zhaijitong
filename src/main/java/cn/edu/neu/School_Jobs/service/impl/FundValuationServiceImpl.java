package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FundValuationMapper;
import cn.edu.neu.School_Jobs.model.FundValuation;
import cn.edu.neu.School_Jobs.service.FundValuationService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class FundValuationServiceImpl extends AbstractService<FundValuation> implements FundValuationService {

    @Autowired
    private FundValuationMapper fundValuationMapper;

}
