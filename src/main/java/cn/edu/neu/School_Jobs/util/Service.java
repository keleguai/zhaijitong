package cn.edu.neu.School_Jobs.util;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;


public interface Service<T> {

    /**
     * 插入数据
     *
     * @param model
     */
    Integer save(T model);

    /**
     * 批量插入数据
     *
     * @param models
     */
    Integer save(List<T> models);

    /**
     * 通过主鍵刪除
     *
     * @param id
     */
    Integer deleteById(Integer id);

    /**
     * 批量刪除
     *
     * @param ids eg：ids -> "1,2,3,4"
     */
    Integer deleteByIds(String ids);

    /**
     * 更新
     *
     * @param model
     */
    Integer update(T model);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    T findById(Integer id);

    T findById(String id);

//    /**
//     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
//     * @param fieldName
//     * @param value
//     * @return
//     * @throws TooManyResultsException
//     */
//    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找
     *
     * @param ids eg：ids -> "1,2,3,4"
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据条件查找
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();


}
