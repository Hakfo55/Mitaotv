package com.mountain.common.exception.pay;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－支付异常类
 * @date 2019/2/23
 */
public class PayException extends ServiceException {

    public PayException() {
    }

    public PayException(int code, String msg) {
        super(code, msg);
    }

    public PayException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public PayException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public PayException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public PayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
