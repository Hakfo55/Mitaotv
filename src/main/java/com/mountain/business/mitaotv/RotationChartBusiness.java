package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.RotationChart;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/14:07
 * @Description:
 */
public interface RotationChartBusiness extends CrudBusiness<RotationChart> {


    /**
     * 根据状态分页查询轮播图列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);



    /**
     * 更新
     * @param rotationChart
     * @return
     */
    RotationChart updateRotationChart(RotationChart rotationChart);
}
