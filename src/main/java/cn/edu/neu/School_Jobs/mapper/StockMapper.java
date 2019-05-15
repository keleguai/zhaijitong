package cn.edu.neu.School_Jobs.mapper;

import cn.edu.neu.School_Jobs.model.Stock;
import cn.edu.neu.School_Jobs.util.MyMapper;

import java.util.List;

public interface StockMapper extends MyMapper<Stock> {
    List<Stock> selectByDisappear();
}
