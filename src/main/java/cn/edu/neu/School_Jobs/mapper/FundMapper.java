package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.util.MyMapper;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface FundMapper extends MyMapper<Fund> {
    List<Fund> selectOrderByPriority();

    List<Fund> selectByField(JSONObject jsonObject);

    List<Fund> selectByAll(JSONObject jsonObject);

    List<Fund> selectByDisStock();

    List<Fund> selectByStockId(JSONObject jsonObject);
}
