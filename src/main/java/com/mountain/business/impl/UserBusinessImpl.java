package com.mountain.business.impl;

import com.mountain.business.UserBusiness;
import com.mountain.po.User;
import com.mountain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.business.impl
 * @Description: TODO 系统－用户
 * @date 2019/7/22
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserBusinessImpl extends CrudBusinessImpl<User, UserRepository> implements UserBusiness {
}
