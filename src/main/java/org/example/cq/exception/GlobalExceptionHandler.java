package org.example.cq.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(SaTokenException.class)
    public SaResult handlerException(SaTokenException e) {
        log.info("satoken异常拦截：" + e.getMessage());
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.info("全局异常拦截：" + e.getMessage());
        return SaResult.error("系统繁忙");
    }

}
