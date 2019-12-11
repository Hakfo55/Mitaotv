package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.VideoBusiness;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Video;
import com.mountain.repository.mitaotv.VideoRepository;
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
 * @Date: 2019/12/04/16:12
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoBusinessImpl extends CrudBusinessImpl<Video,VideoRepository> implements VideoBusiness {

    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Video> videoPage = repository.findByStatus(pageRequest,status);
        return new PageResult(page,videoPage);
    }


    @Override
    public Video update(Video video){
        Assert.notNull(video.getId(),"id不能为空");
        Video oldVideo = repository.findById(video.getId()).orElse(null);
        Assert.notNull(oldVideo,"id错误，视频不存在");
        video.setStatus(oldVideo.getStatus());
        video.setCreatedAt(oldVideo.getCreatedAt());
        video.setUpdatedAt(new Date());
        if (video.getPlayNumber() == 0){
            video.setPlayNumber(oldVideo.getPlayNumber());
        }
        return repository.save(video);
    }

    @Override
    public void increasePlayNum(Long id) {
        Assert.notNull(id,"id不能为空");
        Video video = repository.findById(id).orElse(null);
        Assert.notNull(video,"id错误，视频不存在");
        video.setPlayNumber(video.getPlayNumber() + 1);
        repository.save(video);
    }

}
