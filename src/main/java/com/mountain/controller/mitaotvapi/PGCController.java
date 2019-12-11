package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.PGCBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.PGC;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:52
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/pgc")
@Api(description = "PGC api")
public class PGCController {
    @Autowired
    PGCBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询PGC")
//    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findOne(id));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "新增PGC操作")
//    @IgnoreSecurity
    public ResultBean<Object> insert(@Valid PGC pgc) throws ServiceException {
        return ResultBean.defaultSuccess(business.insertT(pgc));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新PGC操作")
//    @IgnoreSecurity
    public ResultBean<Object> update(@Valid PGC pgc)  {
        return ResultBean.defaultSuccess(business.update(pgc));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询PGC列表")
//    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){

        return ResultBean.defaultSuccess(business.findListByStatus(page,size,state));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除PGC操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.delete(id);
        return ResultBean.defaultSuccess();
    }
}
