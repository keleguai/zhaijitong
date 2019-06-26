package cn.edu.neu.School_Jobs.service;
import cn.edu.neu.School_Jobs.model.FixedFund;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.FixedFundJoinFundVo;

import java.util.List;

/**
 *
 * Created by fzb on 2019/06/05.
 */
public interface FixedFundService extends Service<FixedFund> {
    List<FixedFundJoinFundVo> findByUserId(String userId);
}
