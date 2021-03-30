package com.cjcoder.elephant.exception;

/**
 * @Author: Jeffery
 * @Date: 11:28 2021/3/10
 * @Description:
 */
public abstract class RestBizException extends RuntimeException {
    private static final long serialVersionUID = -59757351490764931L;
    private final int code;

    public RestBizException(int code) {
        this.code = code;
    }

    public RestBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public RestBizException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RestBizException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}