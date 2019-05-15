package cn.edu.neu.School_Jobs.service;

import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.util.Service;

import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
public interface StockService extends Service<Stock> {
    List<Stock> selectByDisappear();
}
