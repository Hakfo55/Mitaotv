package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.PGC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:31
 * @Description:
 */
public interface PGCRepository extends JpaRepository<PGC,Long> {
    Page<PGC> findByStatus(Pageable pageable, String status);
}
