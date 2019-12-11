package com.mountain.common.exception;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/16:32
 * @Description:
 */
public class AboutException extends ServiceException{

    public AboutException() {
    }

    public AboutException(int code, String msg) {
        super(code, msg);
    }

    public AboutException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public AboutException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public AboutException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public AboutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
