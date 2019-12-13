package com.mountain.common.exception.account;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－管理后台用户异常类
 * @date 2019/2/23
 */
public class AccountException extends ServiceException {

    public AccountException() {
    }

    public AccountException(int code, String msg) {
        super(code, msg);
    }

    public AccountException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public AccountException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public AccountException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
