package com.mountain.po.admin;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author porridge
 * @version V1.0
 * @Title: Account.java
 * @Package com.slarts.po
 * @Description: TODO 系统－用户
 * @date 2016年10月28日 上午9:49:34
 */
@Entity
@Table(name = "mitaotv_accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;//用户ID
    @NotBlank
    @Column(unique = true, length = 255)
    private String loginName;//帐户
    @NotBlank
    private String realname;//真实姓名
    private String headImg;//头像
    private String password;//用户密码
    private String salt;//随机加盐码
    private Integer userStat;//用户状态 1正常 2不可用
    private Date createdAt;//用户注册时间
    private Date updatedAt;//用户修改时间
    private String remark;//备注
    //手机号
    private String phone;
    // 0男 1女
    private Integer gender;
    //邮箱
    private String email;
    //部门名称
    private String department;
    //private Long schoolId; //所属学校
    //private Long edClassId;  //所属班级
    private Integer userType;  // 用户类型 （1：管理员，2：子账号）
    //余额
    private BigDecimal balance;


    /**
     * 清除敏感信息
     */
    public void cleanInfo() {
        this.salt = null;
        this.password = null;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserStat() {
        return userStat;
    }

    public void setUserStat(Integer userStat) {
        this.userStat = userStat;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	/*public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getEdClassId() {
		return edClassId;
	}

	public void setEdClassId(Long edClassId) {
		this.edClassId = edClassId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}*/
}
