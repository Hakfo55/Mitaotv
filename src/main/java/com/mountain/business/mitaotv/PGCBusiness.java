package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.PGC;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:49
 * @Description:
 */
public interface PGCBusiness extends CrudBusiness<PGC> {

    /**
     * 根据状态分页查询轮播图列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);


    /**
     * 更新
     * @param pgc
     * @return
     */
    PGC update(PGC pgc);

}
