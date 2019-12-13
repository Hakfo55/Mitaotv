package com.mountain.controller.web;

import com.mountain.business.admin.AccountBusiness;
import com.mountain.business.admin.FunctionBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.helper.TokenHelper;
import com.mountain.common.helper.UserHelper;
import com.mountain.common.util.response.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 主页
 */
@RestController
@RequestMapping("/web")
@Api(description = "登录退出")
public class HomeController {

    @Autowired
    AccountBusiness accountBusiness;
    @Autowired
    FunctionBusiness functionBusiness;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserHelper userHelper;


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("登录")
    @IgnoreSecurity
    public Map<String, Object> login(@RequestParam String loginName, @RequestParam String password, HttpServletRequest request) throws ServiceException {
        return accountBusiness.login(loginName, password,request);
    }

    @RequestMapping(value = "out", method = RequestMethod.GET)
    @ApiOperation("退出")
    public ResultBean out(HttpSession session) {
        String token = tokenHelper.getToken();
        userHelper.logout(token);
        return ResultBean.defaultSuccess();
    }


//    @GetMapping("/getVerify")
//    @IgnoreSecurity
//    @ApiOperation("验证码")
//    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            //设置相应类型,告诉浏览器输出的内容为图片
//            response.setContentType("image/jpeg");
//            //设置响应头信息，告诉浏览器不要缓存此内容
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expire", 0);
//            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
//            //输出验证码图片方法
//            randomValidateCode.getRandCode(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
