package org.example.cq.model.vo;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel(description = "员工登录返回的数据格式")
public class EmployeeLoginVO implements Serializable {

    // @ApiModelProperty("主键值")
    private Long id;

    // @ApiModelProperty("用户名")
    private String userName;

    // @ApiModelProperty("姓名")
    private String name;

    // @ApiModelProperty("jwt令牌")
    private String token;
}