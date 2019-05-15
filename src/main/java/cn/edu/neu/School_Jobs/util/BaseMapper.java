package cn.edu.neu.School_Jobs.util;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
