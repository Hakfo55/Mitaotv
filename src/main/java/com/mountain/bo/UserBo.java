package com.mountain.bo;

import io.swagger.annotations.ApiParam;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.bo
 * @Description: TODO 系统－用户bo
 * @date 2019/7/22
 */
public class UserBo {
    @ApiParam(value = "用户id")
    private Long id;
    @ApiParam(value = "用户名")
    private String name;
    @ApiParam(value = "appId")
    private String appId;
    @ApiParam(value = "备注")
    private String remark;
    @ApiParam(value = "token")
    private String token;
    @ApiParam(value = "secret")
    private String secret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
