package org.example.cq.service;

import org.example.cq.common.PageResult;
import org.example.cq.model.dto.category.CategoryDTO;
import org.example.cq.model.dto.category.CategoryPageQueryDTO;
import org.example.cq.model.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 添加分类
     *
     * @param category
     */
    void save(CategoryDTO category);

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新分类
     *
     * @param category
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用或禁用分类
     *
     * @param id
     * @param status
     */
    void startOrStop(Long id, Integer status);

    /**
     * 根据类型查询分类
     *
     * @param type
     * @return
     */
    List<Category> list(Integer type);


}
