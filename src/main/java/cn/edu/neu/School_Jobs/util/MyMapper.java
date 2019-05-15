package cn.edu.neu.School_Jobs.util;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {
}
