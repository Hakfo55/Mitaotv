package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.ArtistBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ArtistException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.Artist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/14:36
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/artist")
@Api(description = "艺人api")
public class ArtistController {
    @Autowired
    ArtistBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询艺人")
    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增艺人操作")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "portraitUrl",value = "艺人写真图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> insert(@Valid Artist artist,
                                     String portraitUrl) throws ArtistException {
        return ResultBean.defaultSuccess(business.insert(artist,portraitUrl));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新艺人操作")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "portraitUrl",value = "艺人写真图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> update(@Valid Artist artist,
                                     String portraitUrl) throws ArtistException {
        return ResultBean.defaultSuccess(business.update(artist,portraitUrl));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询艺人列表")
    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){
        return ResultBean.defaultSuccess(business.findListByStatus(page,size,state));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除艺人操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.deleteArtist(id);
        return ResultBean.defaultSuccess();
    }

}
