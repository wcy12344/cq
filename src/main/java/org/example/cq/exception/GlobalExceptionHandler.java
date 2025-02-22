package org.example.cq.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(SaTokenException.class)
    public SaResult handlerException(SaTokenException e) {
        log.info("token异常拦截：{}", e.getMessage());
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public SaResult handlerException(HttpRequestMethodNotSupportedException e) {
        return SaResult.error("请求方法不支持");
    }
    @ExceptionHandler(BaseException.class)
    public SaResult handlerException(BaseException e) {
        log.info("业务异常拦截：{}", e.getMessage());
        return new SaResult(e.getCode(),e.getMessage(),null);
    }
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.info("全局异常拦截：{}", e.getMessage());
        return SaResult.error("系统繁忙");
    }

}
