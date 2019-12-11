package com.mountain.business.mitaotv.impl;


import com.mountain.business.impl.CrudBusinessImpl;
import com.mountain.business.mitaotv.NewsBusiness;
import com.mountain.common.exception.NewsException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.Status;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.News;
import com.mountain.po.mitaotv.NewsImageList;
import com.mountain.repository.mitaotv.NewsRepository;
import com.mountain.vo.NewsVO;
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
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:44
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsBusinessImpl extends CrudBusinessImpl<News,NewsRepository> implements NewsBusiness {
    @Autowired
    NewsImageBusinessImpl imageBusiness;

    @Override
    public NewsVO findById(Long id) {
        Assert.notNull(id,"id不能为空");
        News news = repository.findById(id).orElse(null);
        Assert.notNull(news,"id错误，新闻不存在");
        NewsVO newsVO = new NewsVO();
        List<NewsImageList> imageLists =  imageBusiness.findByNewsIdAndStatus(news.getId(),news.getStatus());
        BeanUtils.copyProperties(news,newsVO);
        newsVO.setImageLists(imageLists);
        return newsVO;
    }

    @Override
    public PageResult findListByStatus(Integer page, Integer size, String status) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"priority"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"updatedAt"));
        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.by(orders));
        Page<News> newsPage = repository.findByStatus(pageRequest,status);
        return new PageResult(page,newsPage);
    }

    private NewsVO saveImageList(News news,String newsImageUrl,String operation) throws NewsException {
        List<NewsImageList> imageLists =  imageBusiness.save(news.getId(),newsImageUrl,operation);
        NewsVO newsVO = new NewsVO();
        BeanUtils.copyProperties(news,newsVO);
        newsVO.setImageLists(imageLists);
        return newsVO;
    }

    @Override
    public NewsVO insert(News news, String newsImageUrl) throws NewsException {
        news.setStatus(Status.NORMAL.getValue());
        Date date = new Date();
        news.setCreatedAt(date);
        news.setUpdatedAt(date);
        News result = repository.save(news);

        return saveImageList(result,newsImageUrl,"insert");

    }

    @Override
    public NewsVO update(News news, String newsImageUrl) throws NewsException {
        Assert.notNull(news.getId(),"id不能为空");
        News oldNews = repository.findById(news.getId()).orElse(null);
        Assert.notNull(oldNews,"id错误，新闻不存在");
        news.setStatus(oldNews.getStatus());
        news.setCreatedAt(oldNews.getCreatedAt());
        news.setUpdatedAt(new Date());
        News result = repository.save(news);
        return saveImageList(result,newsImageUrl,"update");
    }

    @Override
    public void deleteNews(Long id) throws ServiceException {
        delete(id);
        imageBusiness.deleteByNewsId(id);
    }

}
