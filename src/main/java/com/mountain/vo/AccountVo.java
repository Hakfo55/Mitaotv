package com.mountain.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: lzt
 * @Description
 * @Date: 2019-04-29 14:19
 */
public class AccountVo {

    //旧密码
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 15, message = "旧密码密码长度为6-15个字符")
    private String oldPassword;

    //新密码
    @NotNull(message = "新密码不能为空")
    @Length(min = 6, max = 15, message = "新密码长度为6-15个字符")
    private String newPassword;

    //新密码
    @NotNull(message = "重复密码不能为空")
    @Length(min = 6, max = 15, message = "重复密码长度为6-15个字符")
    private String reNewPassword;

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
