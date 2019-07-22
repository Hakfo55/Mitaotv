package com.mountain.common.exception.order;

import com.mountain.common.exception.ServiceException;

/**
 * @author chzz
 * @version V1.0
 * @Title:
 * @Package com.mountain.common.exception.account
 * @Description: TODO 系统－订单异常类
 * @date 2019/2/23
 */
public class OrderException extends ServiceException {

    public OrderException() {
    }

    public OrderException(int code, String msg) {
        super(code, msg);
    }

    public OrderException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public OrderException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public OrderException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public OrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
