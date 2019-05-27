package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.Fund;
import cn.edu.neu.School_Jobs.util.Service;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface FundService extends Service<Fund> {
    List<Fund> selectOrderByPriority();

    List<Fund> selectByField(String type,String managerId);

    List<Fund> selectByAll(String regex);

    List<Fund> selectByDisStock();

    List<Fund> selectByStockId(String stockId);
}
