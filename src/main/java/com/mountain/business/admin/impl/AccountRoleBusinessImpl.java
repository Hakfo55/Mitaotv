package com.mountain.business.admin.impl;

import com.mountain.business.admin.AccountRoleBusiness;
import com.mountain.po.admin.AccountRole;
import com.mountain.repository.admin.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: lzt
 * @Description
 * @Date: 2019-04-26 10:35
 */
@Service
@Transactional
public class AccountRoleBusinessImpl implements AccountRoleBusiness {

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public AccountRole save(AccountRole accountRole) {
        return accountRoleRepository.save(accountRole);
    }
}
