package com.mountain.business.admin.impl;

import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.business.admin.RoleBusiness;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.helper.QueryHelper;
import com.mountain.common.util.SystemUtil;
import com.mountain.po.admin.Role;
import com.mountain.po.admin.RoleFun;
import com.mountain.repository.admin.RoleFunRepository;
import com.mountain.repository.admin.RoleRepository;
import com.mountain.vo.GridParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class RoleBusinessImpl implements RoleBusiness {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    QueryHelper queryHelper;
    @Autowired
    RoleFunRepository roleFunRepository;

    @Override
    public GridBo list(GridParamsVo params) {
        HashMap<String, Object> fixed = new HashMap<>();
        return queryHelper.queryGrid(params, Role.class, fixed, "name");
    }

    @Override
    public Role save(Role role) {
        Date date = new Date();
        role.setUpdatedAt(date);
        role.setCreatedAt(date);
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role update(Role role) throws CommonException {
        Role oldRole = roleRepository.findById(role.getRoleId()).get();
        Assert.notNull(oldRole);
        SystemUtil.mergeBean(role, oldRole);
        role.setUpdatedAt(new Date());
        roleRepository.save(role);
        return role;
    }

    @Override
    public void del(Integer roleId) {
        Role oldRole = roleRepository.findById(roleId).get();
        Assert.notNull(oldRole);
        roleRepository.deleteById(roleId);
        delRoleFun(roleId);
    }

    @Override
    public void addFun(Integer roleId, Integer[] funIds) {
        //删除角色下所有的权限，重新分配权限
        delRoleFun(roleId);
        for (Integer funId : funIds) {
            RoleFun r = new RoleFun(roleId, funId);
            roleFunRepository.save(r);
        }
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    /**
     * 删除角色下的所有权限
     *
     * @param roleId
     */
    private void delRoleFun(Integer roleId) {
        List<RoleFun> roleFuns = roleFunRepository.findByRoleId(roleId);
        for (RoleFun r : roleFuns) {
            roleFunRepository.delete(r);
        }
    }

    @Override
    public List<TreeBo> findRoleFuns(Integer roleId) {
        List<Object[]> objs = roleRepository.findRoleFuns(roleId);
        List<TreeBo> trees = new ArrayList<>();
        for (Object[] obj : objs) {
            trees.add(new TreeBo(obj[0], obj[1], obj[2], obj[3]));
        }
        return trees;
    }
}
