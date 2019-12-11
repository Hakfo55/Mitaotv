package com.mountain.business.mitaotv.impl;

import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.RecruitBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.PGC;
import com.mountain.po.mitaotv.Recruit;
import com.mountain.repository.mitaotv.RecruitRepository;
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
 * @Date: 2019/12/05/14:34
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecruitBusinessImpl extends CrudBusinessImpl<Recruit, RecruitRepository> implements RecruitBusiness {

    @Override
    public Recruit update(Recruit recruit) {
        Assert.notNull(recruit.getId(),"id不能为空");
        Recruit oldRecruit = repository.findById(recruit.getId()).orElse(null);
        Assert.notNull(oldRecruit,"id错误，招聘信息不存在");
        recruit.setCreatedAt(oldRecruit.getCreatedAt());
        recruit.setStatus(oldRecruit.getStatus());
        recruit.setUpdatedAt(new Date());
        return repository.save(recruit);
    }

    @Override
    public PageResult findListByStatusAndDevice(Integer page, Integer size, String status,Integer device) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Recruit> recruitPage = null;
        if (device != null){
            recruitPage = repository.findByStatusAndDevice(pageRequest,status,device);
        }else {
            recruitPage =  repository.findByStatus(pageRequest, status);
        }
        return new PageResult(page,recruitPage);
    }
}
