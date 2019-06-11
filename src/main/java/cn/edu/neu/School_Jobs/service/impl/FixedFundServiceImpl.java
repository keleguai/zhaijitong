package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.FixedFundMapper;
import cn.edu.neu.School_Jobs.model.FixedFund;
import cn.edu.neu.School_Jobs.service.FixedFundService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.vo.FixedFundJoinFundVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * Created by zzc on 2019/06/05.
 */
@Service
public class FixedFundServiceImpl extends AbstractService<FixedFund> implements FixedFundService {

    @Autowired
    private FixedFundMapper fixedFundMapper;

    @Override
    public List<FixedFundJoinFundVo> findByUserId(String userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        return fixedFundMapper.findByUserId(jsonObject);
    }
}
