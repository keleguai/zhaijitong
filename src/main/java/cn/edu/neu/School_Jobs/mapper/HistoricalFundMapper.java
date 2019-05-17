package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.HistoricalFund;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.HistoryFundJoinFundVo;

import java.util.List;

public interface HistoricalFundMapper extends MyMapper<HistoricalFund> {
    List<HistoryFundJoinFundVo> findFundWithHistoryData();
}
