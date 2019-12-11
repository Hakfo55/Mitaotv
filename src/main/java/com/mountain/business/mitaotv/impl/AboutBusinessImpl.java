package com.mountain.business.mitaotv.impl;

import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.AboutBusiness;
import com.mountain.business.mitaotv.RecruitBusiness;
import com.mountain.common.exception.AboutException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.helper.TokenHelper;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.About;
import com.mountain.po.mitaotv.AboutImagelist;
import com.mountain.po.mitaotv.Recruit;
import com.mountain.repository.mitaotv.AboutRepository;
import com.mountain.repository.mitaotv.RecruitRepository;
import com.mountain.vo.AboutVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class AboutBusinessImpl extends CrudBusinessImpl<About, AboutRepository> implements AboutBusiness {
    @Autowired
    AboutImageBusinessImpl aboutImageBusiness;

    private AboutVO saveAbout(About about,String aboutImageUrl,String operation) throws AboutException {
        List<AboutImagelist> imagelists =  aboutImageBusiness.save(about.getId(),aboutImageUrl,operation);
        AboutVO aboutVO = new AboutVO();
        BeanUtils.copyProperties(about,aboutVO);
        aboutVO.setImagelists(imagelists);
        return aboutVO;
    }

    @Override
    public AboutVO update(About about,String aboutImageUrl) throws ServiceException {
        Assert.notNull(about.getId(),"id不能为空");
        About oldAbout = repository.findById(about.getId()).orElse(null);
        Assert.notNull(oldAbout,"id错误，关于蜜淘不存在");

        if (oldAbout.getDevice() != about.getDevice()){
            About isExist =  repository.findByDeviceAndStatus(about.getDevice(),Status.NORMAL.getValue());
            if (isExist!=null) {
                deleteAbout(isExist.getId());
            }
        }

        about.setCreatedAt(oldAbout.getCreatedAt());
        about.setStatus(oldAbout.getStatus());
        about.setUpdatedAt(new Date());
        About result = repository.save(about);

        return saveAbout(result,aboutImageUrl,"update");
    }

    @Override
    public AboutVO findById(Long id) {
        Assert.notNull(id,"id不能为空");
        About about = repository.findById(id).orElse(null);
        Assert.notNull(about,"id错误，关于蜜淘不存在");
        AboutVO aboutVO = new AboutVO();
        List<AboutImagelist> imagelists = aboutImageBusiness.findByAboutIdAndStatus(about.getId(),about.getStatus());
        BeanUtils.copyProperties(about,aboutVO);
        aboutVO.setImagelists(imagelists);
        return aboutVO;
    }

    @Override
    public AboutVO insert(About about, String aboutImageUrl) throws ServiceException {
        About isExist = repository.findByDeviceAndStatus(about.getDevice(),Status.NORMAL.getValue());
        if (isExist != null){
            deleteAbout(isExist.getId());
        }

        about.setStatus(Status.NORMAL.getValue());
        Date date = new Date();
        about.setCreatedAt(date);
        about.setUpdatedAt(date);
        About result = repository.save(about);

        return saveAbout(result,aboutImageUrl,"insert");
    }


    @Override
    public PageResult findListByStatusAndDevice(Integer page, Integer size, String status, Integer device) {

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<About> aboutPage = null;
        if (device != null){
            aboutPage  =  repository.findByStatusAndDevice(pageRequest,status,device);
        }else {
            aboutPage = repository.findByStatus(pageRequest, status);
        }
        return new PageResult(page,aboutPage);
    }

    @Override
    public void deleteAbout(Long id) throws ServiceException {
        delete(id);
        aboutImageBusiness.deleteByAboutId(id);
    }
}
