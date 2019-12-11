package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.AboutBusiness;
import com.mountain.business.mitaotv.AboutImageBusiness;
import com.mountain.business.mitaotv.NewsImageBusiness;
import com.mountain.common.exception.AboutException;
import com.mountain.common.exception.NewsException;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.po.mitaotv.About;
import com.mountain.po.mitaotv.AboutImagelist;
import com.mountain.po.mitaotv.News;
import com.mountain.po.mitaotv.NewsImageList;
import com.mountain.repository.mitaotv.AboutImageListRepository;
import com.mountain.repository.mitaotv.NewsImageListRepository;
import org.apache.commons.lang.StringUtils;
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
 * @Date: 2019/12/04/16:36
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AboutImageBusinessImpl extends CrudBusinessImpl<AboutImagelist, AboutImageListRepository> implements AboutImageBusiness {
    @Autowired
    AboutBusinessImpl aboutBusiness;


    @Override
    public Page<AboutImagelist> findByAboutIdAndStatus(Integer page, Integer size, Long aboutId, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        return repository.findByAboutIdAndStatus(pageRequest,aboutId,status);
    }



    @Override
    public List<AboutImagelist> save(Long aboutId, String imageUrl,String operation) throws AboutException {
        if (StringUtils.isBlank(imageUrl)){
            throw new AboutException(GlobalStatusCode.CODE_400002.code(),"图片链接不能为空");
        }

        if("update".equals(operation)) {
            //先删除原来的数据
            repository.deleteByAboutId(aboutId);
        }

        String newUrl = imageUrl.replace("，",",");
        String[] imagesUrl = newUrl.split(",");

        List<AboutImagelist> aboutImagelists = new ArrayList<>(imagesUrl.length);
        for (String url : imagesUrl){
            AboutImagelist aboutImage = new AboutImagelist();
            aboutImage.setAboutId(aboutId);
            aboutImage.setImageUrl(url);
            insert(aboutImage);
            aboutImagelists.add(aboutImage);
        }
        return aboutImagelists;
    }

    @Override
    public List<AboutImagelist> findByAboutIdAndStatus(Long aboutId, String status) {
        return repository.findByAboutIdAndStatus(aboutId,status);
    }

    @Override
    public void deleteByAboutId(Long aboutId) {
        List<AboutImagelist> imagelists =  repository.findByAboutId(aboutId);
        for (AboutImagelist image : imagelists){
            image.setStatus(Status.DELETE.getValue());
            repository.save(image);
        }
    }
}
