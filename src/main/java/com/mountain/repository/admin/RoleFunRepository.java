package com.mountain.repository.admin;

import com.mountain.po.admin.RoleFun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleFunRepository extends JpaRepository<RoleFun, Integer> {
    List<RoleFun> findByRoleId(Integer roleId);
}
