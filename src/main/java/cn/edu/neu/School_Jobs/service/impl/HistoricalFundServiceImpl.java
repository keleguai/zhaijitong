package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.HistoricalFundMapper;
import cn.edu.neu.School_Jobs.model.HistoricalFund;
import cn.edu.neu.School_Jobs.service.HistoricalFundService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.HistoryJoinFundVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class HistoricalFundServiceImpl extends AbstractService<HistoricalFund> implements HistoricalFundService {

    @Autowired
    private HistoricalFundMapper historicalFundMapper;

    @Override
    public List<HistoryJoinFundVo> findAllFundJoinHistory() {
        return historicalFundMapper.findAllFundJoinHistory();
    }

}
