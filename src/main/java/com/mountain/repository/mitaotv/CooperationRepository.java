package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.Cooperation;
import com.mountain.po.mitaotv.Recruit;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface CooperationRepository extends JpaRepository<Cooperation,Long> {
    Page<Cooperation> findByStatusAndDevice(Pageable pageable, String status, Integer device);

    Page<Cooperation> findByStatus(Pageable pageable, String status);
}
