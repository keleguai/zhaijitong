package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.BuyFundJoinVo;

import java.util.HashMap;
import java.util.List;

public interface BuyOrderMapper extends MyMapper<BuyOrder> {
    List<BuyFundJoinVo> selectByUserId(int userId);

    HashMap getSumByBuyMoney(int userId);

    HashMap getSumByNetMoney(int userId);

    HashMap getSumByNetMoneyAndFid(int userId, String fundId);
}
