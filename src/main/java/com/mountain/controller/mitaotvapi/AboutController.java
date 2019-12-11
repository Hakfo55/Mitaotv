package com.mountain.controller.mitaotvapi;

import com.mountain.bo.UserBo;
import com.mountain.business.mitaotv.AboutBusiness;
import com.mountain.business.mitaotv.RecruitBusiness;
import com.mountain.common.annotation.CurrentUser;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.AboutException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.About;
import com.mountain.po.mitaotv.Recruit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/14:35
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/about")
@Api(description = "关于蜜淘api")
public class AboutController {
    @Autowired
    AboutBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询")
//    @IgnoreSecurity
    public ResultBean<Object> findById(@RequestParam("id") Long id){
        return ResultBean.defaultSuccess(business.findById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据状态分页查询列表")
//    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state,
                                   @ApiParam(name = "device",value = "设备信息,1为pc端,2为手机端")@RequestParam(name = "device",required = false) Integer device){
        return ResultBean.defaultSuccess(business.findListByStatusAndDevice(page,size,state,device));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "aboutImageUrl",value = "关于蜜淘图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> insert(@Valid About about,
                                     String aboutImageUrl) throws ServiceException {
        return ResultBean.defaultSuccess(business.insert(about,aboutImageUrl));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "aboutImageUrl",value = "关于蜜淘图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> update(@Valid About about,
                                     String aboutImageUrl) throws ServiceException {

        return ResultBean.defaultSuccess(business.update(about,aboutImageUrl));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除")
//    @IgnoreSecurity
    public ResultBean<Object> delete(@RequestParam("id") Long id) throws ServiceException {
        business.deleteAbout(id);
        return ResultBean.defaultSuccess();
    }


}
