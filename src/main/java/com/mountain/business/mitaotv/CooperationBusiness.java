package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Cooperation;
import com.mountain.po.mitaotv.Recruit;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface CooperationBusiness extends CrudBusiness<Cooperation> {

    /**
     * 更新
     * @param cooperation
     * @return
     */
    Cooperation update(Cooperation cooperation);

    /**
     * 根据状态分页查询列表
     * @return
     */
    PageResult findListByStatusAndDevice(Integer page, Integer size, String status, Integer device);
}
