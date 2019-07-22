package com.mountain.po;

import javax.persistence.*;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.po
 * @Description: TODO 系统－用户主表
 * @date 2019/7/22
 */
@Entity
@Table(name = "book_users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
