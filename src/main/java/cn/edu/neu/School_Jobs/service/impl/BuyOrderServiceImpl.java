package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.BuyOrderMapper;
import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinFundVo;
import cn.edu.neu.School_Jobs.vo.BuyOrderJoinHistoryFundJoinFundVo;
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
public class BuyOrderServiceImpl extends AbstractService<BuyOrder> implements BuyOrderService {

    @Autowired
    private BuyOrderMapper buyOrderMapper;

    @Override
    public List<BuyOrderJoinFundVo> findOrdersWithFundInfo(int userId) {
        return buyOrderMapper.findOrdersWithFundInfo(userId);
    }

    @Override
    public List<BuyOrder> findHistoryOrder(int day, int userId, boolean confirmSign) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("day", day);
        jsonObject.put("userId", userId);
        jsonObject.put("confirmSign", confirmSign);
        return buyOrderMapper.findHistoryOrder(jsonObject);
    }

    @Override
    public HashMap getSumByBuyMoney(int userId) {
        return buyOrderMapper.getSumByBuyMoney(userId);
    }

    @Override
    public HashMap getSumByNetMoney(int userId) {
        return buyOrderMapper.getSumByNetMoney(userId);
    }

    @Override
    public HashMap getSumByNetMoneyAndFid(int userId, String fundId) {
        return buyOrderMapper.getSumByNetMoneyAndFid(userId, fundId);
    }

    @Override
    public List<BuyOrderJoinHistoryFundJoinFundVo> findAllHasBuyFund(int userId) {
        return buyOrderMapper.findAllHasBuyFund(userId);
    }

    @Override
    public List<BuyOrder> findOneFundOrders(int userId, String fundId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fundId", fundId);
        jsonObject.put("userId", userId);
        return buyOrderMapper.findOneFundOrders(jsonObject);
    }

    @Override
    public BuyOrder initialBuyOrderForInsert(BuyOrder buyOrder,int userId){
        buyOrder.setUserId(userId);
        buyOrder.setTimeBuying(new Date());
        buyOrder.setConfirmSign(false);
        buyOrder.setConfirmTheNet(null);
        buyOrder.setResidualShare(-1.f);
        buyOrder.setTimeConfirm(null);
        return buyOrder;
    }
}
