package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.StockMapper;
import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.service.StockService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class StockServiceImpl extends AbstractService<Stock> implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<Stock> selectByDisappear() {
        return stockMapper.selectByDisappear();
    }

}
