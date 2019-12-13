package com.mountain.business.admin;

import com.mountain.po.admin.AccountRole;

/**
 * @Author: lzt
 * @Description
 * @Date: 2019-04-26 10:34
 */
public interface AccountRoleBusiness {

    /**
     * 保存用户的权限
     *
     * @param accountRole
     * @return
     */
    AccountRole save(AccountRole accountRole);
}
