package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface SellOrderMapper extends MyMapper<SellOrder> {
    HashMap selectHasSellMoney(int userId);

    List<SellOrder> findOrdersWithFundInfo(int userId);

    List<SellOrder> findHistoryOrder(JSONObject jsonObject);
}
