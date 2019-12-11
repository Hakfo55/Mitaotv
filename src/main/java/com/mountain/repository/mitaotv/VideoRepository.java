package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:09
 * @Description:
 */
public interface VideoRepository extends JpaRepository<Video,Long> {
    Page<Video> findByStatus(Pageable pageable, String status);
}
