package org.example.cq.common;

import lombok.Data;


/**
 * 通用返回类，服务端响应数据返回对象
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code = 0; // 0 成功 其他失败
    private String msg;
    private T data;

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = code;
        return result;
    }
}
