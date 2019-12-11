package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.NewsException;
import com.mountain.po.mitaotv.NewsImageList;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:34
 * @Description:
 */
public interface NewsImageBusiness extends CrudBusiness<NewsImageList> {


    /**
     * 根据状态分页查询新闻图片列表
     * @return
     */
    Page<NewsImageList> findByNewsIdAndStatus(Integer page, Integer size, Long newsId, String status);

    /**
     * 保存
     * @param newsId
     * @param newsImageUrl
     * @return
     */
    List<NewsImageList> save(Long newsId, String newsImageUrl,String operation) throws NewsException;

    /**
     * 通过新闻id和状态查找新闻图片列表
     * @param newsId
     * @param status
     * @return
     */
    List<NewsImageList> findByNewsIdAndStatus(Long newsId, String status);

    /**
     * 通过新闻id逻辑删除新闻图片
     * @param newsId
     */
    void deleteByNewsId(Long newsId);

}
