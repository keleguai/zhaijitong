package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.SellOrderMapper;
import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.SellOrderJoinHistoryFund;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class SellOrderServiceImpl extends AbstractService<SellOrder> implements SellOrderService {

    @Autowired
    private SellOrderMapper sellOrderMapper;

    @Override
    public HashMap selectHasSellMoney(int userId) {
        return sellOrderMapper.selectHasSellMoney(userId);
    }

    @Override
    public List<SellOrder> findOrdersWithFundInfo(int userId) {
        return sellOrderMapper.findOrdersWithFundInfo(userId);
    }

    @Override
    public List<SellOrder> findHistoryOrder(int day, int userId, String confirmSign,String fundId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("day", day);
        jsonObject.put("userId", userId);
        jsonObject.put("confirmSign", confirmSign);
        jsonObject.put("fundId",fundId);
        return sellOrderMapper.findHistoryOrder(jsonObject);
    }
    @Override
    public SellOrder initialSellOrder(SellOrder sellOrder,int user_id){
        sellOrder.setUserId(user_id);
        sellOrder.setOrderId(null);
        sellOrder.setSellTime(new Date());
        sellOrder.setSureNet(-1.f);
        sellOrder.setServiceCharge(-1.f);
        sellOrder.setConfirmSign(0);
        return sellOrder;
    }

    @Override
    public List<SellOrderJoinHistoryFund> selectOrderWithHistoryFundByField(String userId,String confirmSign){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("confirm",confirmSign);
        jsonObject.put("userId",userId);
        return sellOrderMapper.selectOrderWithHistoryFundByField(jsonObject);
    }
}
