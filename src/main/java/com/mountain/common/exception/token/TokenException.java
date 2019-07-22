package com.mountain.common.exception.token;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－认证校验异常类
 * @date 2019/2/23
 */
public class TokenException extends ServiceException {

    public TokenException() {
    }

    public TokenException(int code, String msg) {
        super(code, msg);
    }

    public TokenException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public TokenException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public TokenException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
