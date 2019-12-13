package com.mountain.common.exception.function;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－管理后台菜单异常类
 * @date 2019/2/23
 */
public class FunctionException extends ServiceException {

    public FunctionException() {
    }

    public FunctionException(int code, String msg) {
        super(code, msg);
    }

    public FunctionException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public FunctionException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public FunctionException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public FunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
