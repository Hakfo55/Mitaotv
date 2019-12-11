package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.PGC;
import com.mountain.po.mitaotv.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface RecruitRepository extends JpaRepository<Recruit,Long> {
    Page<Recruit> findByStatusAndDevice(Pageable pageable, String status, Integer device);

    Page<Recruit> findByStatus(Pageable pageable, String status);
}
