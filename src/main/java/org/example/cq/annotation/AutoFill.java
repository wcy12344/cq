package org.example.cq.annotation;

import org.example.cq.model.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标识方法来自动处理公共冗余字段
 */
@Target(ElementType.METHOD) // 注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时有效
 public @interface AutoFill {
    // 标识操作类型
    OperationType value();
}
