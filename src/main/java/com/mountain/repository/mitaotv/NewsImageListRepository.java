package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.ArtistPortrait;
import com.mountain.po.mitaotv.NewsImageList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:33
 * @Description:
 */
public interface NewsImageListRepository extends JpaRepository<NewsImageList,Long> {
    Page<NewsImageList> findByNewsIdAndStatus(Pageable pageable, Long newsId, String status);

    List<NewsImageList> findByNewsIdAndStatus(Long newsId, String status);

    List<NewsImageList> findByNewsId(Long newsId);

    void deleteByNewsId(Long newsId);

}
