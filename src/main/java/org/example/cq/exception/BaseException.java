package org.example.cq.exception;

import org.example.cq.common.ErrorCode;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
