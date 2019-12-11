package com.mountain.business.mitaotv.impl;


import com.mountain.bo.GridBo;
import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.RotationChartBusiness;
import com.mountain.common.helper.QueryHelper;
import com.mountain.common.util.QiniuyunTokenUtil;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.admin.Role;
import com.mountain.po.mitaotv.RotationChart;
import com.mountain.repository.mitaotv.RotationChartRepository;
import com.mountain.vo.GridParamsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/14:20
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RotationChartBusinessImpl extends CrudBusinessImpl<RotationChart,RotationChartRepository> implements RotationChartBusiness {
    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<RotationChart> rotationChartPage = repository.findByStatus(pageRequest,status);
        return new PageResult(page,rotationChartPage);
    }


    @Override
    public RotationChart updateRotationChart(RotationChart rotationChart)  {
        Assert.notNull(rotationChart.getId(),"id不能为空");
        RotationChart oldRotationChart = repository.findById(rotationChart.getId()).orElse(null);
        Assert.notNull(oldRotationChart,"id错误，轮播图不存在");
        rotationChart.setCreatedAt(oldRotationChart.getCreatedAt());
        rotationChart.setStatus(oldRotationChart.getStatus());
        rotationChart.setUpdatedAt(new Date());
        return repository.save(rotationChart);
    }

}
