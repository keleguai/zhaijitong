package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FundHoldingMapper;
import cn.edu.neu.School_Jobs.model.FundHolding;
import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.service.FundHoldingService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class FundHoldingServiceImpl extends AbstractService<FundHolding> implements FundHoldingService {

    @Autowired
    private FundHoldingMapper fundHoldingMapper;

    @Override
    public List<Stock> showStocks(String fundId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fundId", fundId);
        return fundHoldingMapper.showStocks(jsonObject);
    }

}
