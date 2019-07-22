package com.mountain.controller.api;

import com.mountain.business.UserBusiness;
import com.mountain.common.exception.token.TokenException;
import com.mountain.common.util.response.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.controller.api
 * @Description: TODO 系统－用户
 * @date 2019/7/22
 */
@RestController
@RequestMapping("/api/user")
@Api(description = "用户api")
public class UserApi {

    @Autowired
    UserBusiness userBusiness;

    @ApiOperation(value = "测试")
    @GetMapping
    public ResultBean<Object> sign() {
        return ResultBean.defaultSuccess(userBusiness.findOne(1L));
    }
}
