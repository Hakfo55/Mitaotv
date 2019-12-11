package com.mountain.business.mitaotv.impl;

import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.PartnerBusiness;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Partner;
import com.mountain.repository.mitaotv.PartnerRepository;
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
 * @Date: 2019/12/04/15:40
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PartnerBusinessImpl extends CrudBusinessImpl<Partner,PartnerRepository> implements PartnerBusiness {

    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Partner> partnerPage = repository.findByStatus(pageRequest,status);
        return new PageResult(page,partnerPage);
    }


    @Override
    public Partner update(Partner partner) {
        Assert.notNull(partner.getId(),"id不能为空");
        Partner oldPartner = repository.findById(partner.getId()).orElse(null);
        Assert.notNull(oldPartner,"id错误，合作伙伴不存在");
        partner.setStatus(oldPartner.getStatus());
        partner.setCreatedAt(oldPartner.getCreatedAt());
        partner.setUpdatedAt(new Date());
        return repository.save(partner);
    }

}
