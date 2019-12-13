package com.mountain.business.admin;


import com.mountain.common.exception.common.CommonException;
import com.mountain.common.exception.function.FunctionException;
import com.mountain.po.admin.Function;
import com.mountain.vo.FunctionVo;

import java.util.List;

/**
 * @author porridge
 * @version V1.0
 * @Title: FunctionBusiness.java
 * @Package com.slarts.business
 * @Description: TODO 菜单
 * @date 2016年10月28日 上午10:00:22
 */
public interface FunctionBusiness {
    /**
     * 保存菜单
     *
     * @param fun
     * @return
     */
    Function save(Function fun);

    /**
     * 修改菜单
     *
     * @param fun
     * @return
     */
    Function update(Function fun) throws CommonException;

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    void del(Integer id) throws FunctionException;

    /**
     * 查询列表
     *
     * @return
     */
    List<FunctionVo> list();

    /**
     * 查询用户权限列表
     *
     * @param accountId
     * @return
     */
    List<FunctionVo> findByUserFuns(Integer accountId);

    /**
     * 查询用户权限列表
     *
     * @param accountId
     * @return
     */
    List<Function> listUserFuns(Integer accountId);

    /**
     * 排序
     *
     * @param funIds
     */
    void sort(Integer[] funIds);
}
