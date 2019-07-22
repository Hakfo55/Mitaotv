package com.mountain.business.impl;

import com.mountain.bo.GridBo;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.helper.QueryHelper;
import com.mountain.common.util.ReflectUtils;
import com.mountain.common.util.Status;
import com.mountain.po.BaseEntity;
import com.mountain.vo.GridParamsVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCrudBusiness<D extends JpaRepository<T,Long>, T extends BaseEntity> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected D repository;

    @Autowired
    protected QueryHelper queryHelper;

    protected Class<T> entityClass;

    public BaseCrudBusiness() {
        this.setEntityClass(ReflectUtils.getClassGenricType(this.getClass(), 1));
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 新增
     * @param entity
     * @return
     */
    protected T insert(T entity){
        preInsert(entity);
        return repository.save(entity);
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    protected T update(T entity){
        preUpdate(entity);
        return repository.save(entity);
    }

    /**
     * 获取分页列表
     * @param params
     * @return
     */
    protected GridBo grid(GridParamsVo params, Map<String, Object> fixeds, String... searchNames){
        if (fixeds == null){
            fixeds = new HashMap<>();
        }
        if (!fixeds.containsKey("status")){
            fixeds.put("status", Status.NORMAL.getValue());
        }

        return queryHelper.queryGrid(params, entityClass, fixeds, searchNames);
    }

    /**
     * 根据条件查询列表数据
     * @param entity
     * @return
     */
    protected List<T> findList(T entity){
        if (StringUtils.isBlank(entity.getStatus())){
            entity.setStatus(Status.NORMAL.getValue());
        }
        Example<T>  example = Example.of(entity);
        return repository.findAll(example);
    }

    /**
     * 根据条件查询 数量
     * @param entity
     * @return
     */
    protected long count(T entity){
        if (StringUtils.isBlank(entity.getStatus())){
            entity.setStatus(Status.NORMAL.getValue());
        }
        Example<T>  example = Example.of(entity);
        return repository.count(example);
    }

    /**
     * 逻辑删除
     * @param id
     */
    protected void delete(Long id) throws ServiceException {
        T entity = repository.findOne(id);
        entity.setStatus(Status.DELETE.getValue());
        repository.save(entity);
    }

    /**
     * 物理删除
     * @param id
     */
    protected void phyDelete(Long id) throws ServiceException {
        repository.delete(id);
    }

    /**
     * 批量物理删除
     * @param iterable
     */
    protected void phyDeleteBatch(Iterable<T> iterable){
        repository.delete(iterable);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    protected T findOne(Long id){
        return repository.findOne(id);
    }

    /**
     * 根据条件查询,获取不到返回空值
     * @param
     * @return
     */
    protected T findOne(T entity){
        List<T> list =  findList(entity);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 批量插入
     * @param iterable
     * @return
     */
    protected Iterable<T> insertBatch(Iterable<T> iterable){
        for (T entity : iterable){
            preInsert(entity);
        }
        return repository.save(iterable);
    }

    /**
     * 批量更新
     * @param iterable
     * @return
     */
    protected Iterable<T> updateBatch(Iterable<T> iterable){
        for (T entity : iterable){
            preUpdate(entity);
        }
        return repository.save(iterable);
    }

    /**
     * 插入前执行
     * @param entity
     */
    protected void preInsert(T entity) {
        entity.setStatus(Status.NORMAL.getValue());
        entity.setCreatedAt(new Date());
//        entity.setCreatedBy(UserUtils.getCurrentUserId());
        entity.setUpdatedAt(entity.getCreatedAt());
        entity.setUpdateBy(entity.getCreatedBy());
    }

    /**
     * 更新前执行
     * @param entity
     */
    protected void preUpdate(T entity) {
        entity.setUpdatedAt(new Date());
//        entity.setUpdateBy(UserUtils.getCurrentUserId());
    }
}
