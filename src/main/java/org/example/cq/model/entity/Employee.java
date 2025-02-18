package org.example.cq.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id; // 主键
    private String username; // 用户名
    private String name; // 姓名
    private String password; // 密码
    private String phone; // 手机号
    private String sex; // 性别
    private String idNumber; //身份证
    private Integer status; // 状态 0:禁用，1:正常
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
