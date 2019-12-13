package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.MusicBusiness;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Music;
import com.mountain.repository.mitaotv.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/15:08
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MusicBusinessImpl extends CrudBusinessImpl<Music,MusicRepository> implements MusicBusiness {

    @Override
    public PageResult findList(Integer page, Integer size, String status,String singer) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Music> musicPage = null;
        if (StringUtils.isEmpty(singer)){
            musicPage = repository.findByStatus(pageRequest,status);
        }else {
            musicPage = repository.findBySingerAndStatus(pageRequest,singer,status);
        }
        return new PageResult(page,musicPage);
    }

    @Override
    public Music update(Music music) {
        Assert.notNull(music.getId(),"id不能为空");
        Music oldMusic = repository.findById(music.getId()).orElse(null);
        Assert.notNull(oldMusic,"id错误，音乐不存在");
        music.setCreatedAt(oldMusic.getCreatedAt());
        music.setStatus(oldMusic.getStatus());
        music.setUpdatedAt(new Date());
        return repository.save(music);
    }

    @Override
    public PageResult singerList(Integer page, Integer size,String status) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<String> musicPage = repository.singerList(pageRequest,status);
        return new PageResult(page,musicPage);
    }

}
