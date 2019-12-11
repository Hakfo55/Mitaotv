package com.mountain.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Entity
 * @Description TODO
 * @Author xiaoxie
 * @Date 2019/4/17 10:58
 * @Version 1.0
 */
@MappedSuperclass
@ApiModel
public class BaseEntity implements Serializable {
    @ApiModelProperty(hidden = true)
    protected String status;   // 状态状态（0正常 1删除 2停用）
    @ApiModelProperty(hidden = true)
    protected Long createdBy;  // 创建者
    @ApiModelProperty(hidden = true)
    protected Long updateBy;  // 更新者
    @ApiModelProperty(hidden = true)
    protected Date createdAt;   // 创建日期
    @ApiModelProperty(hidden = true)
    protected Date updatedAt;   // 更新日期

    public BaseEntity() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
