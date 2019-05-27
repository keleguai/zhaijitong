package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.util.Service;
import cn.edu.neu.School_Jobs.vo.SellOrderJoinHistoryFund;

import java.util.HashMap;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface SellOrderService extends Service<SellOrder> {
    HashMap selectHasSellMoney(int userId);

    List<SellOrder> findOrdersWithFundInfo(int userId);

    List<SellOrder> findHistoryOrder(int day, int userId, int confirmSign);

    SellOrder initialSellOrder(SellOrder sellOrder,int userId);

    List<SellOrderJoinHistoryFund> selectOrderWithHistoryFundByField(String userId,String confirmSign);
}
