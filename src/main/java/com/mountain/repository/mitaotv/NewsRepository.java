package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:38
 * @Description:
 */
public interface NewsRepository extends JpaRepository<News,Long> {
    Page<News> findByStatus(Pageable pageable, String status);
}
