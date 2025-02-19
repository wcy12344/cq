package org.example.cq.exception;

import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.ErrorCode;
import org.example.cq.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


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
    public Result<?> handlerException(BaseException e) {
        log.error("BaseException", e);
        return Result.error(e.getCode(), e.getMessage());
    }

}
