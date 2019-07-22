package com.mountain.common.util;

/**
 * @ClassName Status
 * @Description TODO
 * @Author xiaoxie
 * @Date 2019/4/17 14:29
 * @Version 1.0
 */
public enum Status {
    NORMAL("0"),  //正常
    DELETE("1"),	//删除
    FORBIDDEN("2"), //停用
    REMARK("3"), //标记
    REMARK_DELETE("4"); //标记删除

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
