package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.ArtistBusiness;
import com.mountain.business.mitaotv.ArtistPortraitBusiness;
import com.mountain.common.exception.ArtistException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Artist;
import com.mountain.po.mitaotv.ArtistPortrait;
import com.mountain.repository.mitaotv.ArtistRepository;
import com.mountain.vo.ArtistVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
 * @Date: 2019/12/03/18:19
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArtistBusinessImpl extends CrudBusinessImpl<Artist,ArtistRepository> implements ArtistBusiness {
    @Autowired
    ArtistPortraitBusinessImpl portraitBusiness;


    @Override
    public ArtistVo findById(Long id) {
        Assert.notNull(id,"id不能为空");
        Artist artist = repository.findById(id).orElse(null);
        Assert.notNull(artist,"id错误，艺人不存在");
        ArtistVo artistVo = new ArtistVo();
        List<ArtistPortrait> portraitList =  portraitBusiness.findByArtistIdAndStatus(artist.getId(),artist.getStatus());
        BeanUtils.copyProperties(artist,artistVo);
        artistVo.setArtistPortraitList(portraitList);
        return artistVo;
    }

    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<Artist> artistPage =  repository.findByStatus(pageRequest,status);
        return new PageResult(page,artistPage);
    }

    private ArtistVo savePortratisList(Artist artist, String portraitUrl,String operation) throws ArtistException {
        List<ArtistPortrait> artistPortraitList  = portraitBusiness.save(artist.getId(),portraitUrl,operation);
        ArtistVo artistVo = new ArtistVo();
        BeanUtils.copyProperties(artist,artistVo);
        artistVo.setArtistPortraitList(artistPortraitList);
        return artistVo;
    }

    @Override
    public ArtistVo insert(Artist artist, String portraitUrl) throws ArtistException {
        artist.setStatus(Status.NORMAL.getValue());
        Date date = new Date();
        artist.setCreatedAt(date);
        artist.setUpdatedAt(date);
        Artist result = repository.save(artist);

        return savePortratisList(result,portraitUrl,"insert");
    }

    @Override
    public ArtistVo update(Artist artist, String portraitUrl) throws ArtistException {
        Assert.notNull(artist.getId(),"id不能为空");
        Artist oldArtist = repository.findById(artist.getId()).orElse(null);
        Assert.notNull(oldArtist,"id错误，艺人不存在");
        artist.setStatus(oldArtist.getStatus());
        artist.setCreatedAt(oldArtist.getCreatedAt());
        artist.setUpdatedAt(new Date());
        Artist result = repository.save(artist);

        return savePortratisList(result,portraitUrl,"update");
    }

    @Override
    public void deleteArtist(Long id) throws ServiceException {
        delete(id);
        portraitBusiness.deleteByArtistId(id);
    }

}
