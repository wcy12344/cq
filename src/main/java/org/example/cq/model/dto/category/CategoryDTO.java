package org.example.cq.model.dto.category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private Long id; // 主键
    private String name; // 分类名称
    private Integer type; // 分类
    private Integer sort; // 排序
}
