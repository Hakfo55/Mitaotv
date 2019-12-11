package com.mountain.common.exception;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/16:13
 * @Description:
 */
public class ArtistException extends ServiceException {

    public ArtistException() {
    }

    public ArtistException(int code, String msg) {
        super(code, msg);
    }

    public ArtistException(String message, int code, String msg) {
        super(message, code, msg);
    }

    public ArtistException(String message, Throwable cause, int code, String msg) {
        super(message, cause, code, msg);
    }

    public ArtistException(Throwable cause, int code, String msg) {
        super(cause, code, msg);
    }

    public ArtistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace, code, msg);
    }
}
