package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinFundVo;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinHistoryFundJoinFundVo;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface BuyOrderMapper extends MyMapper<BuyOrder> {
    List<BuyOrderJoinFundVo> findOrdersWithFundInfo(int userId);

    HashMap getSumByBuyMoney(int userId);

    HashMap getSumByNetMoney(int userId);

    HashMap getSumByNetMoneyAndFid(int userId, String fundId);

    List<BuyOrderJoinHistoryFundJoinFundVo> findAllHasBuyFund(int userId);

    List<BuyOrder> findOneFundOrders(JSONObject jsonObject);

    List<BuyOrder> findHistoryOrder(JSONObject jsonObject);

    List<BuyOrderJoinHistoryFundJoinFundVo> selectOrdersLeftJoinHistoryFundByField(JSONObject jsonObject);
}
