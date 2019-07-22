package com.mountain.common.exception.common;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－工具异常类
 * @date 2019/2/23
 */
public class CommonException extends ServiceException {

    public CommonException() {
    }

    public CommonException(int code, String msg) {
        super(code, msg);
    }

    public CommonException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public CommonException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public CommonException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
