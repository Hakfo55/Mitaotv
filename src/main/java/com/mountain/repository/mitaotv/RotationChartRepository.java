package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.RotationChart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/14:06
 * @Description:
 */
public interface RotationChartRepository extends JpaRepository<RotationChart,Long> {

    Page<RotationChart> findByStatus(Pageable pageable, String status);

}
