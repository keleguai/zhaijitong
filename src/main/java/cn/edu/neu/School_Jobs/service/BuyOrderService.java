package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinFundVo;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinHistoryFundJoinFundVo;

import java.util.HashMap;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface BuyOrderService extends Service<BuyOrder> {
    List<BuyOrderJoinFundVo> findOrdersWithFundInfo(int userId);

    HashMap getSumByBuyMoney(int userId);

    HashMap getSumByNetMoney(int userId);

    HashMap getSumByNetMoneyAndFid(int userId, String fundId);

    List<BuyOrderJoinHistoryFundJoinFundVo> findAllHasBuyFund(int userId);

    List<BuyOrder> findOneFundOrders(int userId, String fundId);

    List<BuyOrder> findHistoryOrder(int day, int userId, boolean confirmSign);

    BuyOrder initialBuyOrderForInsert(BuyOrder buyOrder,int userId);

    List<BuyOrderJoinHistoryFundJoinFundVo> selectOrdersLeftJoinHistoryFundByField(String userId,String confirmSign);
}
