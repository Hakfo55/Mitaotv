package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.About;
import com.mountain.po.mitaotv.Cooperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface AboutRepository extends JpaRepository<About,Long> {
    Page<About> findByStatusAndDevice(Pageable pageable, String status, Integer device);

    Page<About> findByStatus(Pageable pageable, String status);

    About findByDeviceAndStatus(Integer device, String status);
}
