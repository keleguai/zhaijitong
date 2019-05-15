package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.BuyFundJoinVo;

import java.util.HashMap;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface BuyOrderService extends Service<BuyOrder> {
    List<BuyFundJoinVo> selectByUserId(int userId);

    HashMap getSumByBuyMoney(int userId);

    HashMap getSumByNetMoney(int userId);

    HashMap getSumByNetMoneyAndFid(int userId, String fundId);

}
