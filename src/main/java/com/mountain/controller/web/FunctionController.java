package com.mountain.controller.web;

import com.mountain.business.admin.FunctionBusiness;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.exception.function.FunctionException;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.admin.Function;
import com.mountain.vo.FunctionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/web/function")
@Api(description = "菜单管理")
public class FunctionController {
    @Autowired
    FunctionBusiness functionBusiness;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("新增菜单")
    public ResultBean<Function> save(@Valid Function fun) {
        return ResultBean.defaultSuccess(functionBusiness.save(fun));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("修改菜单")
    public ResultBean<Function> update(@Valid Function fun) throws CommonException {
        return ResultBean.defaultSuccess(functionBusiness.update(fun));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation("删除菜单")
    public ResultBean<Object> del(@RequestParam Integer id) throws FunctionException {
        functionBusiness.del(id);
        return ResultBean.defaultSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "grid", method = RequestMethod.GET)
    @ApiOperation("获取所有菜单")
    public ResultBean<List<FunctionVo>> grid() {
        List<FunctionVo> list = functionBusiness.list();
        return ResultBean.defaultSuccess(list);
    }
}
