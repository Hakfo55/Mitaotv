package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.ArtistPortraitBusiness;
import com.mountain.common.exception.ArtistException;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.po.mitaotv.Artist;
import com.mountain.po.mitaotv.ArtistPortrait;
import com.mountain.repository.mitaotv.ArtistPortraitRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:38
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArtistPortraitBusinessImpl extends CrudBusinessImpl<ArtistPortrait,ArtistPortraitRepository> implements ArtistPortraitBusiness {
    @Autowired
    ArtistBusinessImpl artistBusiness;

    @Override
    public Page<ArtistPortrait> findByArtistIdAndStatus(Integer page, Integer size, Long artistId, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        return repository.findByArtistIdAndStatus(pageRequest,artistId,status);
    }


    @Override
    public List<ArtistPortrait> save(Long artistId, String portraitUrl,String operation) throws ArtistException {
        if (StringUtils.isBlank(portraitUrl)){
            throw new ArtistException(GlobalStatusCode.CODE_400002.code(),"写真图片链接不能为空");
        }

        if("update".equals(operation)) {
            //先删除原来的数据
            repository.deleteByArtistId(artistId);
        }

        String newUrl = portraitUrl.replace("，",",");
        String[] imagesUrl = newUrl.split(",");

        List<ArtistPortrait> artistPortraitList = new ArrayList<>(imagesUrl.length);
        for (String url:imagesUrl){
            ArtistPortrait artistPortrait = new ArtistPortrait();
            artistPortrait.setArtistId(artistId);
            artistPortrait.setPortraitUrl(url);
            insert(artistPortrait);
            artistPortraitList.add(artistPortrait);
        }
        return artistPortraitList;
    }

    @Override
    public List<ArtistPortrait> findByArtistIdAndStatus(Long artistId, String status) {
        return repository.findByArtistIdAndStatus(artistId,status);
    }

    @Override
    public void deleteByArtistId(Long artistId) {
        List<ArtistPortrait> portraitList =  repository.findByArtistId(artistId);
        for (ArtistPortrait portrait:portraitList){
            portrait.setStatus(Status.DELETE.getValue());
            repository.save(portrait);
        }
    }

}
