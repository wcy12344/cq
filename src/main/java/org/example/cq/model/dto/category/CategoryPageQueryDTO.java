package org.example.cq.model.dto.category;

import lombok.Data;

@Data
public class CategoryPageQueryDTO {
    private Integer type;
    private String name;
    private Integer current;
    private Integer pageSize;
}
