package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.PGC;
import com.mountain.po.mitaotv.Recruit;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface RecruitBusiness extends CrudBusiness<Recruit> {

    /**
     * 更新
     * @param recruit
     * @return
     */
    Recruit update(Recruit recruit);

    /**
     * 根据状态分页查询列表
     * @return
     */
    PageResult findListByStatusAndDevice(Integer page, Integer size, String status, Integer device);
}
