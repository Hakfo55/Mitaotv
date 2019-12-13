package com.mountain.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @author porridge
 * @version V1.0
 * @Title: BreadcrumbBo.java
 * @Package com.slarts.bo
 * @Description: TODO 面包屑
 * @date 2016年11月2日 上午8:30:39
 */
public class BreadcrumbUrlBo implements Serializable {
    private static final long serialVersionUID = -4164445446388893846L;
    private String menuName;
    private String breadcrumbUrl;
    private List<BreadcrumbUrlBo> sonMenus;

    public BreadcrumbUrlBo() {
        super();
    }

    public BreadcrumbUrlBo(String menuName, String breadcrumb, List<BreadcrumbUrlBo> sonMenus) {
        super();
        this.menuName = menuName;
        this.breadcrumbUrl = breadcrumb;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getBreadcrumb() {
        return breadcrumbUrl;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumbUrl = breadcrumb;
    }

    public List<BreadcrumbUrlBo> getSonMenus() {
        return sonMenus;
    }

    public void setSonMenus(List<BreadcrumbUrlBo> sonMenus) {
        this.sonMenus = sonMenus;
    }
}
