package org.example.cq.controller.admin;

import cn.dev33.satoken.util.SaResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.PageResult;
import org.example.cq.model.dto.category.CategoryDTO;
import org.example.cq.model.dto.category.CategoryPageQueryDTO;
import org.example.cq.model.entity.Category;
import org.example.cq.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public SaResult save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新建分类:{}", categoryDTO);
        categoryService.save(categoryDTO);
        return SaResult.ok("新建成功");
    }

    @GetMapping("/page")
    public SaResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询分类:{}", categoryPageQueryDTO);
        PageResult<Category> pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return SaResult.data(pageResult);
    }

    @PutMapping
    public SaResult update(@RequestBody CategoryDTO categoryDTO) {
        log.info("更新分类:{}", categoryDTO);
        categoryService.update(categoryDTO);
        return SaResult.ok("更新成功");
    }

    @GetMapping("/list")
    public SaResult list(Integer type) {
        log.info("查询分类列表:type={}", type);
        List<Category> categoryList = categoryService.list(type);
        return SaResult.data(categoryList);
    }
    @DeleteMapping
    public SaResult delete(@RequestParam Long id) {
        log.info("删除分类:id={}", id);
        categoryService.deleteById(id);
        return SaResult.ok("删除成功");
    }
    @PutMapping("/{status}")
    public SaResult updateStatus(@PathVariable Integer status, @RequestParam Long id) {
        log.info("更新分类状态:id={},status={}", id, status);
        categoryService.startOrStop(id, status);
        return SaResult.ok("更新成功");
    }
}
