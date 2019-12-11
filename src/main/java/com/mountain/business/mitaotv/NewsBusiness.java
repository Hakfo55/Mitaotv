package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.NewsException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.News;
import com.mountain.vo.ArtistVo;
import com.mountain.vo.NewsVO;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:39
 * @Description:
 */
public interface NewsBusiness extends CrudBusiness<News> {

    /**
     * 通过id查找新闻
     * @return
     */
    NewsVO findById(Long id);

    /**
     * 根据状态分页查询新闻列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);

    /**
     *
     * @param news
     * @param newsImageUrl
     * @return
     * @throws NewsException
     */
    NewsVO insert(News news, String newsImageUrl) throws NewsException;


    /**
     * 更新
     * @param artist
     * @param newsImageUrl
     * @return
     * @throws NewsException
     */
    NewsVO update(News artist, String newsImageUrl) throws NewsException;

    /**
     * 逻辑删除新闻以及新闻图片
     * @param id
     */
    void deleteNews(Long id) throws ServiceException;
}
