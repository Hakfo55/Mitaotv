package com.mountain.business.admin.impl;

import com.mountain.bo.BreadcrumbUrlBo;
import com.mountain.bo.GridBo;
import com.mountain.bo.TreeBo;
import com.mountain.bo.UserBo;
import com.mountain.business.admin.AccountBusiness;
import com.mountain.business.admin.FunctionBusiness;
import com.mountain.common.config.ApplicationConfig;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.exception.account.AccountException;
import com.mountain.common.exception.common.CommonException;
import com.mountain.common.helper.QueryHelper;
import com.mountain.common.helper.TokenHelper;
import com.mountain.common.helper.UserHelper;
import com.mountain.common.util.SystemUtil;
import com.mountain.common.util.response.GlobalStatusCode;
import com.mountain.po.admin.Account;
import com.mountain.po.admin.AccountRole;
import com.mountain.po.admin.Function;
import com.mountain.repository.admin.AccountRepository;
import com.mountain.repository.admin.AccountRoleRepository;
import com.mountain.vo.GridParamsVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
public class AccountBusinessImpl implements AccountBusiness {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    @Autowired
    QueryHelper queryHelper;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserHelper userHelper;
    @Autowired
    FunctionBusiness functionBusiness;
    @Autowired
    ApplicationConfig config;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Account findByLoginName(String loginName) {
        return accountRepository.findByLoginName(loginName);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findByAccountId(id);
    }

    @Override
    public GridBo list(GridParamsVo params) {
        return queryHelper.queryGrid(params, Account.class, null, "realname", "loginName");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Account save(Account account) throws AccountException {
        if (accountRepository.findByLoginName(account.getLoginName()) != null) {
            throw new AccountException(GlobalStatusCode.CODE_400016.code(), GlobalStatusCode.CODE_400016.value());
        }
        Date date = new Date();
        account.setCreatedAt(date);
        account.setUpdatedAt(date);
        if (StringUtils.isBlank(account.getPassword())) {
            account.setPassword("778899");
        }
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getLoginName());
        account.setHeadImg("https://cdn.xiaolouyou.com/o_1dq44u6af1ee31ttk13ct9vedot7.png");
        account.setSalt(SystemUtil.getRandomStr(6));
        account.setUserStat(1);
        account.setPassword(new SimpleHash("MD5", account.getPassword(), credentialsSalt, 1024).toString());
        account.setUserType(2);
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account update(Account account) throws CommonException {
        Account oldAccount = accountRepository.findByAccountId(account.getAccountId());
        Assert.notNull(oldAccount);
        SystemUtil.mergeBean(account, oldAccount);
        account.setUpdatedAt(new Date());
        accountRepository.save(account);
        return account;
    }

    @Override
    public void addRole(Integer accountId, Integer[] roleIds) {
        List<AccountRole> roles = accountRoleRepository.findByAccountId(accountId);
        for (AccountRole role : roles) {
            accountRoleRepository.delete(role);
        }
        for (Integer roleId : roleIds) {
            AccountRole ar = new AccountRole(accountId, roleId);
            accountRoleRepository.save(ar);
        }
    }

    @Override
    public List<TreeBo> findRole(Integer accountId) {
        List<Object[]> objs = accountRepository.findByRoles(accountId);
        List<TreeBo> trees = new ArrayList<>();
        for (Object[] obj : objs) {
            trees.add(new TreeBo(obj[0], null, obj[1],obj[2]));
        }
        return trees;
    }

    @Override
    public Account updateAdminPassword(String oldPassword, String newPassword, String reNewPssword) throws ServiceException {
        Account account = (Account) SecurityUtils.getSubject().getPrincipal();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getLoginName());
        String MD5OldPassword = new SimpleHash("MD5", oldPassword, credentialsSalt, 1024).toString();
        if (!account.getPassword().equals(MD5OldPassword)) {
            throw new ServiceException(GlobalStatusCode.CODE_80000.value(), GlobalStatusCode.CODE_80000.code(), "密码不正确");
        }
        if (!newPassword.equals(reNewPssword)) {
            throw new ServiceException(GlobalStatusCode.CODE_80000.value(), GlobalStatusCode.CODE_80000.code(), " 两次输入密码不一致");
        }
        account.setPassword(new SimpleHash("MD5", newPassword, credentialsSalt, 1024).toString());
        return accountRepository.save(account);

    }

    @Override
    public void frozenAccount(Integer accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setUserStat(2);
        accountRepository.save(account);
    }

    @Override
    public Map<String, Object> login(String loginName, String password, HttpServletRequest request) throws ServiceException {
        Account account = findByLoginName(loginName);
        if (account == null) {
            throw new ServiceException(GlobalStatusCode.CODE_80003.code(), "账号不存在");
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getLoginName());
        String md5 = new SimpleHash("MD5", password, credentialsSalt, 1024).toString();
        if (!account.getPassword().equals(md5)) {
            throw new ServiceException(GlobalStatusCode.CODE_80003.code(), "密码错误");
        }
//        Boolean verifyCode = verifyCode(request, codeByUser);
//        if (!verifyCode) {
//            throw new ServiceException(GlobalStatusCode.CODE_80003.code(), "验证码错误");
//        }
        String token = tokenHelper.sign(loginName, password, 7) + account.getAccountId();
        userHelper.exchangeToken(new UserBo(new Long(account.getAccountId()), account.getRealname(), account.getLoginName(), "", token,
                account.getPassword(),account.getUserType()));
        Map<String, Object> map = new HashMap<>();
        map.put("Authorization", token);
        map.put("accountId", account.getAccountId());
        map.put("realName", account.getRealname());
        map.put("loginName", account.getLoginName());
        map.put("headImg",account.getHeadImg());

        List<Function> funList = functionBusiness.listUserFuns(account.getAccountId());
        List<String> urlList = new ArrayList<>();
        for (Function function : funList) {
            String url = function.getUrl();
            if (url != null) {
                urlList.add(url);
            }
        }
        //设置权限
        userHelper.setValidUrl(token,urlList);
        map.put("validUrl", getValidUrl(funList));
        return map;
    }

    /**
     * 把有权限的路径转为前端的路径
     * @param funList
     */
    private List<BreadcrumbUrlBo> getValidUrl(List<Function> funList) {
        List<BreadcrumbUrlBo> breadcrumbUrlBos = new ArrayList<>();
        for (Function fun : funList) {
            if (fun.getParentId() != null || fun.getShow() == 2) {
                continue;
            }
            BreadcrumbUrlBo bo = new BreadcrumbUrlBo();
            List<BreadcrumbUrlBo> sonMenus = new ArrayList<>();
            bo.setMenuName(fun.getName());
            bo.setBreadcrumb(config.map.get(fun.getName()));
            bo.setSonMenus(sonMenus);
            breadcrumbUrlBos.add(bo);

            for (Function fun1 : funList) {
                if (fun1.getFunType() == 1 && fun1 != null && fun.getFunId().equals(fun1.getParentId())) {
                    BreadcrumbUrlBo sonMenu = new BreadcrumbUrlBo();
                    sonMenu.setMenuName(fun1.getName());
                    sonMenu.setBreadcrumb(config.map.get(fun1.getName()));
                    sonMenus.add(sonMenu);
                }
            }
        }
        return breadcrumbUrlBos;
    }

    @Override
    public Account updatePassword(Integer accountId, String password) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setUpdatedAt(new Date());
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getLoginName());
        account.setPassword(new SimpleHash("MD5", password, credentialsSalt, 1024).toString());
        accountRepository.save(account);
        return account;
    }

    @Override
    public Boolean resetPassword(Integer accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        ByteSource credentialsSalt = ByteSource.Util.bytes(account.getLoginName());
        account.setPassword(new SimpleHash("MD5", "888888", credentialsSalt, 1024).toString());
        account.setUpdatedAt(new Date());
        accountRepository.save(account);
        return true;
    }

    private Boolean verifyCode(HttpServletRequest request, String codeByUser) {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        if (StringUtils.isNotBlank(code) && code.equals(codeByUser)) {
            return true;
        }
        return false;
    }

    @Override
    public Account find(Integer accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    @Override
    public Boolean isExistLoginName(String loginName) {
        Account accountCriteria = new Account();
        accountCriteria.setLoginName(loginName);
        Example<Account> example = Example.of(accountCriteria);
        Long count = accountRepository.count(example);
        return count > 0 ? true : false;
    }

    @Override
    public List<Map<String, Object>> ListUser() {
        List<Account> accountList = accountRepository.findAll();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Account account : accountList) {
            Map<String,Object> map = new HashMap<>();
            map.put("accountId",account.getAccountId());
            map.put("name",account.getLoginName());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public Boolean changeBalance(Integer accountId, Double money) {
        Account account = findById(accountId);
        BigDecimal temp = new BigDecimal(money);
        account.setBalance(account.getBalance().add(temp));
        accountRepository.save(account);
        return true;
    }
}
