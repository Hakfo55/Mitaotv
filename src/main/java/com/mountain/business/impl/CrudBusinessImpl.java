package com.mountain.business.impl;

import com.mountain.bo.GridBo;
import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.ServiceException;
import com.mountain.po.BaseEntity;
import com.mountain.vo.GridParamsVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.business.impl
 * @Description: TODO 系统－通用模版
 * @date 2019/7/22
 */
public class CrudBusinessImpl<T extends BaseEntity, D extends JpaRepository<T, Long>> extends BaseCrudBusiness<D, T>
        implements CrudBusiness<T> {

    @Override
    public T findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public T updateT(T vo) throws ServiceException {
        return super.update(vo);
    }

    @Override
    public T insertT(T vo) throws ServiceException {
        return super.insert(vo);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        super.delete(id);
    }

    @Override
    public void phyDelete(Long id) throws ServiceException {
        super.phyDelete(id);
    }

    @Override
    public GridBo grid(GridParamsVo params, Map<String, Object> fixeds, String... strings) {
        return super.grid(params, fixeds, strings);
    }

    @Override
    public List<T> findList(T entity) {
        return super.findList(entity);
    }

    @Override
    public T findOne(T entity) {
        return super.findOne(entity);
    }

    @Override
    public Iterable<T> insertBatch(Iterable<T> iterable) {
        return super.insertBatch(iterable);
    }

    @Override
    public Iterable<T> updateBatch(Iterable<T> iterable) {
        return super.updateBatch(iterable);
    }

    @Override
    public long count(T entity) {
        return super.count(entity);
    }

    @Override
    public void phyDeleteBatch(Iterable<T> iterable) {
        super.phyDeleteBatch(iterable);
    }
}
