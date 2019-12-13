package com.mountain.controller.mitaotvapi;

import com.mountain.business.mitaotv.CooperationBusiness;
import com.mountain.business.mitaotv.RecruitBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.Cooperation;
import com.mountain.po.mitaotv.Recruit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:35
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/cooperation")
@Api(description = "商务合作图片列表api")
public class CooperationController {
    @Autowired
    CooperationBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询单个商务合作图片")
    @IgnoreSecurity
    public ResultBean<Object> findById(@RequestParam("id") Long id){
        return ResultBean.defaultSuccess(business.findOne(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据状态分页查询列表")
    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state,
                                   @ApiParam(name = "device",value = "设备信息,1为pc端,2为手机端")@RequestParam(name = "device",required = false) Integer device){

        return ResultBean.defaultSuccess(business.findListByStatusAndDevice(page,size,state,device));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增商务合作图片")
//    @IgnoreSecurity
    public ResultBean<Object> insert(@Valid Cooperation cooperation) throws ServiceException {
        return ResultBean.defaultSuccess(business.insertT(cooperation));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改商务合作图片")
//    @IgnoreSecurity
    public ResultBean<Object> update(@Valid Cooperation cooperation){

        return ResultBean.defaultSuccess(business.update(cooperation));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除商务合作图片")
//    @IgnoreSecurity
    public ResultBean<Object> delete(@RequestParam("id") Long id) throws ServiceException {
        business.delete(id);
        return ResultBean.defaultSuccess();
    }


}
