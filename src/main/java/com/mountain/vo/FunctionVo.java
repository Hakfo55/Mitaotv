package com.mountain.vo;

import com.mountain.po.admin.Function;

import java.io.Serializable;
import java.util.List;

/**
 * @author porridge
 * @version V1.0
 * @Title: FunctionVo.java
 * @Package com.slarts.vo
 * @Description: TODO 管理菜单树结构
 * @date 2016年12月12日 下午5:39:59
 */
public class FunctionVo implements Serializable {
    private static final long serialVersionUID = -1025703974532871101L;

    private Integer funId;
    private String name;//菜单名
    private Integer parentId;//父级id
    private String ico;//小图标
    private String url;//连接地址
    private Integer funType;//菜单类型 1菜单  2按钮
    private Integer show;//菜单列表显示 1显示 2隐藏
    private String method;//请求方法类型
    private Integer sort;
    private List<FunctionVo> funs;
    private Boolean existSon;

    public FunctionVo() {
        super();
    }

    public FunctionVo(Function fun) {
        super();
        this.show = fun.getShow();
        this.funId = fun.getFunId();
        this.name = fun.getName();
        this.parentId = fun.getParentId();
        this.ico = fun.getIco();
        this.url = fun.getUrl();
        this.funType = fun.getFunType();
        this.method = fun.getMethod();
        this.sort = fun.getSort();
    }

    public Boolean getExistSon() {
        return existSon == null ? false : existSon;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public void setExistSon(Boolean existSon) {
        this.existSon = existSon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFunType() {
        return funType;
    }

    public void setFunType(Integer funType) {
        this.funType = funType;
    }

    public List<FunctionVo> getFuns() {
        return funs;
    }

    public void setFuns(List<FunctionVo> funs) {
        if (funs != null && funs.size() > 0) {
            for (FunctionVo fun : funs) {
                if (fun.funType == 1) {
                    this.existSon = true;
                    break;
                }
            }
        }
        this.funs = funs;
    }
}
