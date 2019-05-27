package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FundMapper;
import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.service.FundService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class FundServiceImpl extends AbstractService<Fund> implements FundService {

    @Autowired
    private FundMapper fundMapper;

    public List<Fund> selectOrderByPriority() {
        return fundMapper.selectOrderByPriority();
    }

//    public List<Fund> selectByField(String type) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("type", type);
//        return fundMapper.selectByField(jsonObject);
//    }

    @Override
    public List<Fund> selectByStockId(String stockId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stockId", stockId);
        return fundMapper.selectByStockId(jsonObject);
    }

    @Override
    public List<Fund> selectByDisStock() {
        return fundMapper.selectByDisStock();
    }

    @Override
    public List<Fund> selectByField(String type,String managerId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("managerId",managerId);
        return fundMapper.selectByField(jsonObject);
    }

    @Override
    public List<Fund> selectByAll(String regex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("regex", regex);
        return fundMapper.selectByAll(jsonObject);
    }
}
