package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.HistoricalFund;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.HistoryJoinFundVo;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface HistoricalFundService extends Service<HistoricalFund> {
    List<HistoryJoinFundVo> findAllFundJoinHistory();
}
