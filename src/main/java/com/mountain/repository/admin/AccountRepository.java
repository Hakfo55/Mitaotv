package com.mountain.repository.admin;

import com.mountain.po.admin.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByLoginName(String loginName);

    @Query(value = "select r.role_id,r.name,a.account_role_id from pp_roles r left join pp_account_roles a ON a.role_id=r.role_id AND a.account_id=?1", nativeQuery = true)
    List<Object[]> findByRoles(Integer accountId);


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    Account findByAccountId(Integer id);

}
