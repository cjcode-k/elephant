package com.cjcoder.elephant.exception;

/**
 * @author jefferychan
 * @description: hdfs写入操作失败
 */
public class HdfsOperationException extends RestBizException {

    public static final int CODE = 110001;

    public HdfsOperationException() {
        super(CODE);
    }

    public HdfsOperationException(String message) {
        super(CODE, message);
    }

    public HdfsOperationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }

    public HdfsOperationException(Throwable cause) {
        super(CODE, cause);
    }
}
