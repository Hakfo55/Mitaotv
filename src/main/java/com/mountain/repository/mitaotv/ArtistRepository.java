package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/18:17
 * @Description:
 */
public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Page<Artist> findByStatus(Pageable pageable, String status);
}
