package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.FixedFund;
import cn.edu.neu.School_Jobs.util.MyMapper;
import cn.edu.neu.School_Jobs.vo.FixedFundJoinFundVo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface FixedFundMapper extends MyMapper<FixedFund> {
    List<FixedFundJoinFundVo> findByUserId(JSONObject jsonObject);
}
