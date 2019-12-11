package com.mountain.business.mitaotv.impl;

import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.CooperationBusiness;
import com.mountain.business.mitaotv.RecruitBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Cooperation;
import com.mountain.po.mitaotv.Recruit;
import com.mountain.repository.mitaotv.CooperationRepository;
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
public class CooperationBusinessImpl extends CrudBusinessImpl<Cooperation, CooperationRepository> implements CooperationBusiness {

    @Override
    public Cooperation update(Cooperation cooperation) {
        Assert.notNull(cooperation.getId(),"id不能为空");
        Cooperation oldCooperation = repository.findById(cooperation.getId()).orElse(null);
        Assert.notNull(oldCooperation,"id错误，合作伙伴不存在");

        cooperation.setCreatedAt(oldCooperation.getCreatedAt());
        cooperation.setStatus(oldCooperation.getStatus());
        cooperation.setUpdatedAt(new Date());
        return repository.save(cooperation);
    }

    @Override
    public PageResult findListByStatusAndDevice(Integer page, Integer size, String status,Integer device) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Cooperation> cooperationPage = null;
        if (device != null) {
            cooperationPage =  repository.findByStatusAndDevice(pageRequest, status, device);
        }else{
            cooperationPage = repository.findByStatus(pageRequest,status);
        }
        return new PageResult(page,cooperationPage);
    }
}
