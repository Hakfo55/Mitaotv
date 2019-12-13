package com.mountain.business.admin;

import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.common.exception.common.CommonException;
import com.mountain.po.admin.Role;
import com.mountain.vo.GridParamsVo;

import java.util.List;

/**
 * @author porridge
 * @version V1.0
 * @Title: RoleBusiness.java
 * @Package com.slarts.business
 * @Description: TODO 角色管理
 * @date 2016年10月30日 下午10:11:37
 */
public interface RoleBusiness {
    GridBo list(GridParamsVo params);

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    Role save(Role role);

    /**
     * 修改角色名称
     *
     * @param role
     * @return
     */
    Role update(Role role) throws CommonException;

    /**
     * 删除角色
     *
     * @param roleId
     */
    void del(Integer roleId);

    /**
     * 查询角色的所有权限树结构
     *
     * @param roleId
     * @return
     */
    List<TreeBo> findRoleFuns(Integer roleId);

    /**
     * 为角色添加权限
     *
     * @param roleId
     * @param funIds
     */
    void addFun(Integer roleId, Integer[] funIds);

    /**
     * 根据名称查找角色
     *
     * @param name 角色名称
     * @return
     */
    Role findRoleByName(String name);
}
