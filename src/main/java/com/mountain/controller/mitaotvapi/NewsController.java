package com.mountain.controller.mitaotvapi;


import com.mountain.business.mitaotv.NewsBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.NewsException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.common.util.response.PageResult;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.mitaotv.News;
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
 * @Date: 2019/12/04/11:54
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/news")
@Api(description = "新闻api")
public class NewsController {
    @Autowired
    NewsBusiness business;

    @GetMapping("/findbyid")
    @ApiOperation(value = "通过id查询新闻")
    @IgnoreSecurity
    public ResultBean<Object> findById(Long id) {
        return ResultBean.defaultSuccess(business.findById(id));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增新闻操作")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "newsImageUrl",value = "新闻图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> insert(@Valid News news,
                                     String newsImageUrl) throws NewsException {
        return ResultBean.defaultSuccess(business.insert(news,newsImageUrl));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新新闻操作")
//    @IgnoreSecurity
    @ApiImplicitParam(name = "newsImageUrl",value = "新闻图片列表链接",paramType = "query",dataType = "String")
    public ResultBean<Object> update(@Valid News news,
                                     String newsImageUrl) throws NewsException {
        return ResultBean.defaultSuccess(business.update(news,newsImageUrl));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询新闻列表")
    @IgnoreSecurity
    public ResultBean<Object> list(@ApiParam(name = "page",value = "当前页数") @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @ApiParam(name = "size",value = "每页数量")@RequestParam(name = "size",defaultValue = "10") Integer size,
                                   @ApiParam(name = "state",value = "状态")@RequestParam(name = "state",defaultValue = "0") String state){

        return ResultBean.defaultSuccess( business.findListByStatus(page,size,state));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "逻辑删除新闻操作")
//    @IgnoreSecurity
    public ResultBean<Object> delete(Long id) throws ServiceException {
        business.deleteNews(id);
        return ResultBean.defaultSuccess();
    }
}
