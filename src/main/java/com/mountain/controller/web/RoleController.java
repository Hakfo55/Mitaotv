package com.mountain.controller.web;

import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.business.admin.RoleBusiness;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.admin.Role;
import com.mountain.vo.GridParamsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/web/role")
@Api(description = "角色管理")
public class RoleController {

    @Autowired
    RoleBusiness roleBusiness;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("新增角色")
    public ResultBean<Role> save(@Valid Role role) {
        return ResultBean.defaultSuccess(roleBusiness.save(role));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("修改角色")
    public ResultBean<Role> update(@Valid Role role) throws CommonException {
        return ResultBean.defaultSuccess(roleBusiness.update(role));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation("删除角色")
    public ResultBean<Object> del(@RequestParam Integer roleId) {
        roleBusiness.del(roleId);
        return ResultBean.defaultSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "tree/{roleId}", method = RequestMethod.GET)
    @ApiOperation("获取角色分配的权限")
    public ResultBean<List<TreeBo>> tree(@PathVariable("roleId") Integer roleId) {
        return ResultBean.defaultSuccess(roleBusiness.findRoleFuns(roleId));
    }

    @ResponseBody
    @RequestMapping(value = "tree", method = RequestMethod.POST)
    @ApiOperation("分配角色的权限")
    public ResultBean<Object> addFun(Integer roleId, Integer[] funIds) {
        roleBusiness.addFun(roleId, funIds);
        return ResultBean.defaultSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "grid", method = RequestMethod.POST)
    @ApiOperation("角色列表")
    public GridBo grid(GridParamsVo params) {
        return roleBusiness.list(params);
    }
}
