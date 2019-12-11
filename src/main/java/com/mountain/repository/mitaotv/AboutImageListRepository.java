package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.AboutImagelist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/09/9:45
 * @Description:
 */
public interface AboutImageListRepository extends JpaRepository<AboutImagelist,Long> {
    Page<AboutImagelist> findByAboutIdAndStatus(Pageable pageable,Long aboutId, String status);

    List<AboutImagelist> findByAboutIdAndStatus(Long aboutId, String status);

    List<AboutImagelist> findByAboutId(Long aboutId);

    void deleteByAboutId(Long aboutId);
}
