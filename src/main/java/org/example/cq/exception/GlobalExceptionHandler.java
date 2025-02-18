package org.example.cq.exception;

import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.ErrorCode;
import org.example.cq.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.info("全局异常拦截：" + e.getMessage());
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(BaseException.class)
    public Result<?> businessExceptionHandler(BaseException e) {
        log.error("BaseException", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return Result.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
