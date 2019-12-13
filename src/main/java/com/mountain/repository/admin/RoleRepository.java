package com.mountain.repository.admin;

import com.mountain.po.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select f.fun_id,f.parent_id,f.name,r.role_id from pp_functions f left join pp_role_funs r on r.fun_id=f.fun_id and r.role_id=?1 order by f.fun_sort asc", nativeQuery = true)
    List<Object[]> findRoleFuns(Integer roleId);

    @Query(nativeQuery = true, value = "select role_id,name,created_at,updated_at from pp_roles where name = ?1")
    Role findRoleByName(String name);
}
