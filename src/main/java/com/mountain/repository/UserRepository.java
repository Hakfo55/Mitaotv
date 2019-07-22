package com.mountain.repository;

import com.mountain.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.repository
 * @Description: TODO 系统－用户
 * @date 2019/7/22
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
