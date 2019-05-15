package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.SellOrderMapper;
import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SellOrder> selectByUserId(int userId) {
        return sellOrderMapper.selectByUserId(userId);
    }

}
