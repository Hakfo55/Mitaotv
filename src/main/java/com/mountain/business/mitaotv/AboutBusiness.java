package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.AboutException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.About;
import com.mountain.po.mitaotv.Recruit;
import com.mountain.vo.AboutVO;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:32
 * @Description:
 */
public interface AboutBusiness extends CrudBusiness<About> {

    /**
     * 通过id查找
     * @param id
     * @return
     */
    AboutVO findById(Long id);

    /**
     * 新增
     * @param about
     * @param aboutImageUrl
     * @return
     */
    AboutVO insert(About about, String aboutImageUrl) throws ServiceException;


    /**
     * 更新
     * @param about
     * @return
     */
    AboutVO update(About about, String aboutImageUrl) throws ServiceException;

    /**
     * 根据状态分页查询列表
     * @return
     */
    PageResult findListByStatusAndDevice(Integer page, Integer size, String status, Integer device);


    /**
     * 逻辑删除关于蜜淘以及图片
     * @param id
     */
    void deleteAbout(Long id) throws ServiceException;
}
