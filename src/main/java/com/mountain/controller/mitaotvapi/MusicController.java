package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.MusicBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.Music;
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
 * @Date: 2019/12/04/15:18
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/music")
@Api(description = "音乐api")
public class MusicController {
    @Autowired
    MusicBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询音乐")
    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findOne(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增音乐操作")
//    @IgnoreSecurity
    public ResultBean<Object> insert(@Valid Music music) throws ServiceException {
        return ResultBean.defaultSuccess(business.insertT(music));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新音乐操作")
//    @IgnoreSecurity
    public ResultBean<Object> update(@Valid Music music)  {
        return ResultBean.defaultSuccess(business.update(music));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询音乐列表")
    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state,
                                   @ApiParam(name = "singer",value = "歌手名字")@RequestParam(name = "singer",required = false) String singer){

        return ResultBean.defaultSuccess(business.findList(page,size,state,singer));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除音乐操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.delete(id);
        return ResultBean.defaultSuccess();
    }

    @ApiOperation("获取歌手列表")
    @GetMapping("/singer")
    @IgnoreSecurity
    public ResultBean<Object> singer(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                     @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                     @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){
        return ResultBean.defaultSuccess(business.singerList(page, size, state));
    }
}
