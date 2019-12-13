package com.mountain.business.admin;

import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.exception.account.AccountException;
import com.mountain.common.exception.common.CommonException;
import com.mountain.po.admin.Account;
import com.mountain.vo.GridParamsVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author A18ccms A18ccms_gmail_com
 * @version V1.0
 * @Title: AccountBusiness.java
 * @Package com.slarts.business
 * @Description: TODO 用户帐户
 * @date 2016年10月27日 下午9:32:23
 */
public interface AccountBusiness {

    /**
     * 根据账号获取用户信息
     *
     * @param loginName
     * @return
     */
    Account findByLoginName(String loginName);

    /**
     * 根据id查找用户信息
     *
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    GridBo list(GridParamsVo params);

    /**
     * 添加新的用户
     *
     * @param account
     * @return
     */
    Account save(Account account) throws AccountException;

    /**
     * 根据id查询某一个用户
     *
     * @param accountId
     * @return
     */
    Account find(Integer accountId);

    /**
     * 修改用户
     *
     * @param account
     * @return
     */
    Account update(Account account) throws CommonException;

    /**
     * 修改密码  系统管理员
     *
     * @param accountId
     * @param password
     * @return
     */
    Account updatePassword(Integer accountId, String password);

    /**
     * 为用户添加角色
     *
     * @param accountId
     * @param roleIds
     */
    void addRole(Integer accountId, Integer[] roleIds);

    /**
     * 查询用户角色
     *
     * @param accountId
     * @return
     */
    List<TreeBo> findRole(Integer accountId);

    /**
     * 修改密码  其他管理员
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    Account updateAdminPassword(String oldPassword, String newPassword, String reNewPssword) throws ServiceException;

    /**
     * 根据用户id，冻结用户，禁止登录
     *
     * @param accountId
     */
    void frozenAccount(Integer accountId);

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    Map<String, Object> login(String loginName, String password, HttpServletRequest request) throws ServiceException;


    /**
     * 重置管理员密码
     *
     * @param accountId
     * @return
     */
    Boolean resetPassword(Integer accountId);

    /**
     * 判断是否存在登录名
     *
     * @param loginName
     * @return
     */
    Boolean isExistLoginName(String loginName);

    /**
     * 下拉用户列表
     *
     * @return
     */
    List<Map<String, Object>> ListUser();

    /**
     * 修改金额
     *
     * @param accountId
     * @param money
     * @return
     */
    Boolean changeBalance(Integer accountId, Double money);
}
