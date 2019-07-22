package com.mountain.common.exception.sms;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－短信异常类
 * @date 2019/2/23
 */
public class SmsException extends ServiceException {

    public SmsException() {
    }

    public SmsException(int code, String msg) {
        super(code, msg);
    }

    public SmsException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public SmsException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public SmsException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public SmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
