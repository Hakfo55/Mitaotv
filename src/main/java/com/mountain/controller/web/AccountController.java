package com.mountain.controller.web;

import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.business.admin.AccountBusiness;
import com.mountain.common.annotation.IgnoreSecurity;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.exception.account.AccountException;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.util.response.ResultBean;
import com.mountain.po.admin.Account;
import com.mountain.vo.AccountVo;
import com.mountain.vo.GridParamsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 帐号管理
 */
@Controller
@RequestMapping("/web/account")
@Api(description = "帐号管理")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @ResponseBody
    @RequestMapping(value = "grid", method = RequestMethod.POST)
    @ApiOperation("用户列表")
    public GridBo grid(GridParamsVo vo) {
        return accountBusiness.list(vo);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("新增用户，清除敏感信息")
    public ResultBean<Account> save(@Valid Account account) throws AccountException {
        return ResultBean.defaultSuccess(cleanInfo(accountBusiness.save(account)));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("修改用户，清除敏感信息")
    public ResultBean<Account> update(@Valid Account account) throws CommonException {
        return ResultBean.defaultSuccess(cleanInfo(accountBusiness.update(account)));
    }

    @ResponseBody
    @RequestMapping(value = "password", method = RequestMethod.PUT)
    @ApiOperation("修改密码，清除敏感信息")
    public ResultBean<Account> updatePassword(@RequestParam Integer accountId, @RequestParam String password) {
        return ResultBean.defaultSuccess(cleanInfo(accountBusiness.updatePassword(accountId, password)));
    }

    @ResponseBody
    @RequestMapping(value = "role/{accountId}", method = RequestMethod.GET)
    @ApiOperation("根据用户获取角色")
    public ResultBean<List<TreeBo>> tree(@PathVariable("accountId") Integer accountId) {
        return ResultBean.defaultSuccess(accountBusiness.findRole(accountId));
    }

    @ResponseBody
    @RequestMapping(value = "{accountId}", method = RequestMethod.GET)
    @ApiOperation("根据id查找用户，清除敏感信息")
    public ResultBean<Account> find(@PathVariable("accountId") Integer accountId) {
        return ResultBean.defaultSuccess(cleanInfo(accountBusiness.find(accountId)));
    }

    @ResponseBody
    @RequestMapping(value = "role", method = RequestMethod.POST)
    @ApiOperation("设置角色")
    public ResultBean<Object> addFun(Integer accountId, @RequestParam Integer[] roleIds) {
        accountBusiness.addRole(accountId, roleIds);
        return ResultBean.defaultSuccess();
    }

    /**
     * 清除敏感信息
     *
     * @param account
     */
    private Account cleanInfo(Account account) {
        account.cleanInfo();
        return account;
    }

    @PutMapping("/updateAdminPassword")
    @ResponseBody
    @ApiOperation("修改密码")
    public ResultBean updateAdminPassword(@Valid AccountVo accountVo) throws ServiceException {
        return ResultBean.defaultSuccess(accountBusiness.updateAdminPassword(accountVo.getOldPassword(), accountVo.getNewPassword(), accountVo.getReNewPassword()));
    }

    @PutMapping("/resetPwd/{accountId}")
    @ResponseBody
    @ApiOperation("重置密码")
    public ResultBean<Boolean> resetPassword(@PathVariable Integer accountId) throws ServiceException {
        return ResultBean.defaultSuccess(accountBusiness.resetPassword(accountId));
    }

    @GetMapping("listUser")
    @ResponseBody
    @ApiOperation("用户下拉列表")
    public ResultBean<List<Map<String, Object>>> listUser() {
        return ResultBean.defaultSuccess(accountBusiness.ListUser());
    }
}
