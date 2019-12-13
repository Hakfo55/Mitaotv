package com.mountain.bo;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author porridge
 * @version V1.0
 * @Title: RoleFunTreeBo.java
 * @Package com.slarts.bo
 * @Description: TODO 角色权限树
 * @date 2016年10月31日 下午2:03:02
 */
public class TreeBo implements Serializable {
    private static final long serialVersionUID = -5779566653623257324L;
    private Integer id;
    private Integer pId;
    private String name;
    private Boolean checked;
    private Boolean open;
    private String uid;

    public TreeBo(Object id, Object pId, Object name, Object checked) {
        super();
        this.id = (Integer) id;
        this.pId = pId != null ? (Integer) pId : 0;
        this.name = name.toString();
        this.checked = checked != null ? true : false;
        this.open = true;
        this.uid = UUID.randomUUID().toString();
    }

    public TreeBo() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
