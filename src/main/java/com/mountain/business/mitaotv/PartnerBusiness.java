package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Partner;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/15:29
 * @Description:
 */
public interface PartnerBusiness extends CrudBusiness<Partner> {


    /**
     * 根据状态分页查询新闻列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);


    /**
     * 更新
     * @param partner
     * @return
     */
    Partner update(Partner partner);

}
