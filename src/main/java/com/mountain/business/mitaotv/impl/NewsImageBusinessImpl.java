package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.NewsImageBusiness;
import com.mountain.common.exception.NewsException;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.po.mitaotv.ArtistPortrait;
import com.mountain.po.mitaotv.News;
import com.mountain.po.mitaotv.NewsImageList;
import com.mountain.repository.mitaotv.NewsImageListRepository;
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
 * @Date: 2019/12/04/16:36
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsImageBusinessImpl extends CrudBusinessImpl<NewsImageList,NewsImageListRepository> implements NewsImageBusiness {
    @Autowired
    NewsBusinessImpl newsBusiness;

    @Override
    public Page<NewsImageList> findByNewsIdAndStatus(Integer page, Integer size, Long newsId, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        return repository.findByNewsIdAndStatus(pageRequest,newsId,status);
    }


    @Override
    public List<NewsImageList> save(Long newsId,String newsImageUrl,String operation) throws NewsException {
        if (StringUtils.isBlank(newsImageUrl)){
            throw new NewsException(GlobalStatusCode.CODE_400002.code(),"图片链接不能为空");
        }
        if ("update".equals(operation)) {
            //先删除原来的数据
            repository.deleteByNewsId(newsId);
        }

        String newUrl = newsImageUrl.replace("，",",");
        String[] imagesUrl = newUrl.split(",");

        List<NewsImageList> imageListList = new ArrayList<>(imagesUrl.length);
        for (String url:imagesUrl) {
            NewsImageList newsImageList = new NewsImageList();
            newsImageList.setNewsId(newsId);
            newsImageList.setNewsImageUrl(url);
            insert(newsImageList);
            imageListList.add(newsImageList);
        }
        return imageListList;
    }

    @Override
    public List<NewsImageList> findByNewsIdAndStatus(Long newsId, String status) {
        return repository.findByNewsIdAndStatus(newsId,status);
    }

    @Override
    public void deleteByNewsId(Long newsId) {
        List<NewsImageList> newsList =  repository.findByNewsId(newsId);
        for (NewsImageList newsImage:newsList){
            newsImage.setStatus(Status.DELETE.getValue());
            repository.save(newsImage);
        }
    }

}
