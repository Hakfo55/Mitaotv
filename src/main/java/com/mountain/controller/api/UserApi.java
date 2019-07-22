package com.mountain.controller.api;

import com.mountain.business.UserBusiness;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
