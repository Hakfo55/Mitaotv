package com.mountain.controller.mitaotvapi;

import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.util.QiniuyunTokenUtil;
import com.mountain.common.util.response.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/06/11:26
 * @Description:
 */
@RestController
@RequestMapping("/mitaotv/uptoken")
@Api(description = "七牛云token")
public class QiniuyunTokenController {

    @GetMapping("/gettoken")
    @ApiOperation(value = "七牛云token")
//    @IgnoreSecurity
    public ResultBean<Object> getToken(){
        return ResultBean.defaultSuccess(QiniuyunTokenUtil.getUpToken());
    }
}
