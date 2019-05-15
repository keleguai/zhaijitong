package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.FundHolding;
import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface FundHoldingMapper extends MyMapper<FundHolding> {
    List<Stock> showStocks(JSONObject jsonObject);
}
