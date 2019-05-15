package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.BuyOrderMapper;
import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.BuyFundJoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BuyFundJoinVo> selectByUserId(int userId) {
        return buyOrderMapper.selectByUserId(userId);
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
}
