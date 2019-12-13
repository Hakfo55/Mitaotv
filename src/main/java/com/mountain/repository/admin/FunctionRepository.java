package com.mountain.repository.admin;

import com.mountain.po.admin.Function;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, Integer> {
    List<Function> findByParentId(Integer id);

    @Query("select distinct f from Function f, Account a, AccountRole ar, Role r, RoleFun rf "
            + "where a.accountId=ar.accountId and r.roleId=ar.roleId "
            + "and r.roleId=rf.roleId and f.funId=rf.funId and a.accountId=?1")
    List<Function> findByUserFuns(Integer accountId, Sort sort);
}
