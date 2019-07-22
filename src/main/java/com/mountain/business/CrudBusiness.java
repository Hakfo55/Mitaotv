package com.mountain.business;

import com.mountain.bo.GridBo;
import com.mountain.common.exception.ServiceException;
import com.mountain.po.BaseEntity;
import com.mountain.vo.GridParamsVo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CrudBusiness
 * @Description TODO
 * @Author xiaoxie
 * @Date 2019/4/17 22:12
 * @Version 1.0
 */
public interface CrudBusiness<T extends BaseEntity> {

    /**
     * 通过id获取
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     * 更新
     * @param vo
     */
    T updateT(T vo) throws ServiceException;

    /**
     * 插入
     * @param vo
     */
    T insertT(T vo) throws ServiceException;

    /**
     * 逻辑删除
     * @param id
     */
    void delete(Long id) throws ServiceException;

    /**
     * 物理删除
     * @param id
     */
    void phyDelete(Long id) throws ServiceException;

    /**
     * 获取分页列表
     * @param params
     * @return
     */
    GridBo grid(GridParamsVo params, Map<String, Object> fixeds, String... strings);

    /**
     * 根据条件获取列表
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 根据条件查询,获取不到返回空值
     * @param
     * @return
     */
    T findOne(T entity);

    /**
     * 批量插入
     * @param iterable
     * @return
     */
    Iterable<T> insertBatch(Iterable<T> iterable);

    /**
     * 批量更新
     * @param iterable
     * @return
     */
    Iterable<T> updateBatch(Iterable<T> iterable);

    /**
     * 根据条件查询 数量
     * @param entity
     * @return
     */
    long count(T entity);

    /**
     * 批量物理删除
     * @param iterable
     */
    void phyDeleteBatch(Iterable<T> iterable);
}
