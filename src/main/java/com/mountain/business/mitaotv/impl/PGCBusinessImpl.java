package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.PGCBusiness;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.PGC;
import com.mountain.repository.mitaotv.PGCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:50
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PGCBusinessImpl extends CrudBusinessImpl<PGC,PGCRepository> implements PGCBusiness {

    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<PGC> pgcPage = repository.findByStatus(pageRequest,status);
        return new PageResult(page,pgcPage);
    }


    @Override
    public PGC update(PGC pgc) {
        Assert.notNull(pgc.getId(),"id不能为空");
        PGC oldPGC = repository.findById(pgc.getId()).orElse(null);
        Assert.notNull(oldPGC,"id错误，PGC不存在");
        pgc.setStatus(oldPGC.getStatus());
        pgc.setCreatedAt(oldPGC.getCreatedAt());
        pgc.setUpdatedAt(new Date());
        return repository.save(pgc);
    }

}
