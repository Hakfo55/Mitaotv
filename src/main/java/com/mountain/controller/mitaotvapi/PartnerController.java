package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.PartnerBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.Partner;
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
 * @Date: 2019/12/04/16:04
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/partner")
@Api(description = "合作伙伴api")
public class PartnerController {
    @Autowired
    PartnerBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询合作伙伴")
    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findOne(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增合作伙伴操作")
//    @IgnoreSecurity
    public ResultBean<Object> insert(@Valid Partner partner) throws ServiceException {
        return ResultBean.defaultSuccess(business.insertT(partner));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新合作伙伴操作")
//    @IgnoreSecurity
    public ResultBean<Object> update(@Valid Partner partner)  {
        return ResultBean.defaultSuccess(business.update(partner));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询合作伙伴列表")
    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){

        return ResultBean.defaultSuccess(business.findListByStatus(page,size,state));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除合作伙伴操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.delete(id);
        return ResultBean.defaultSuccess();
    }
}
