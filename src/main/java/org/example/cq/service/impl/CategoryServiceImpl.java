package org.example.cq.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.example.cq.common.PageResult;
import org.example.cq.constant.StatusConstant;
import org.example.cq.exception.BaseException;
import org.example.cq.mapper.CategoryMapper;
import org.example.cq.mapper.DishMapper;
import org.example.cq.mapper.SetmealMapper;
import org.example.cq.model.dto.category.CategoryDTO;
import org.example.cq.model.dto.category.CategoryPageQueryDTO;
import org.example.cq.model.entity.Category;
import org.example.cq.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private DishMapper dishMapper;

    @Resource
    private SetmealMapper setmealMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser(StpUtil.getLoginIdAsLong());
//        category.setUpdateUser(StpUtil.getLoginIdAsLong());
        categoryMapper.insert(category);
    }

    @Override
    public PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getCurrent(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        long total = page.getTotal();
        List<Category> records = page.getResult();
        return new PageResult<>(total, records);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtil.copyProperties(categoryDTO, category);
//        category.setUpdateTime(LocalDateTime.now());
//        category.setUpdateUser(StpUtil.getLoginIdAsLong());
        categoryMapper.update(category);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }

    @Override
    public void startOrStop(Long id, Integer status) {
        Category category = Category.builder()
                .id(id)
                .status(status)
//                .updateTime(LocalDateTime.now())
//                .updateUser(StpUtil.getLoginIdAsLong())
                .build();
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Long id) {
        Integer count = dishMapper.getCountById(id);
        if (count > 0) {
            throw new BaseException(1, "该分类下有菜品，无法删除");
        }
        count = setmealMapper.getCountById(id);
        if (count > 0) {
            throw new BaseException(1, "该分类下有套餐，无法删除");
        }
        // 删除
        categoryMapper.delete(id);
    }
}
