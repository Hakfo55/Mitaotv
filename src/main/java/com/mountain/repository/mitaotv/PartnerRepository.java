package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/15:28
 * @Description:
 */
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    Page<Partner> findByStatus(Pageable pageable, String status);
}
