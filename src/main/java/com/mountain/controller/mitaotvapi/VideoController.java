package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.VideoBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.Video;
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
 * @Date: 2019/12/04/16:26
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/video")
@Api(description = "视频api")
public class VideoController {
    @Autowired
    VideoBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询视频")
//    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findOne(id));
    }

    @PostMapping("/increase")
    @ApiOperation(value = "增加播放量")
//    @IgnoreSecurity
    public ResultBean<Object> increasePlayNum(Long id){
        business.increasePlayNum(id);
        return ResultBean.defaultSuccess();
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增视频操作")
//    @IgnoreSecurity
    public ResultBean<Object> insert(@Valid Video video) throws ServiceException {
        return ResultBean.defaultSuccess(business.insertT(video));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新视频操作")
//    @IgnoreSecurity
    public ResultBean<Object> update(@Valid Video video)  {
        return ResultBean.defaultSuccess(business.update(video));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询视频列表")
//    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){

        return ResultBean.defaultSuccess(business.findListByStatus(page,size,state));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除视频操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.delete(id);
        return ResultBean.defaultSuccess();
    }

}
