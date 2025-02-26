package org.example.cq.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.cq.annotation.AutoFill;
import org.example.cq.model.dto.category.CategoryPageQueryDTO;
import org.example.cq.model.entity.Category;
import org.example.cq.model.enums.OperationType;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     * @param category
     */

    @Insert("insert into category (name, type, sort, status, create_time, update_time, create_user, update_user) " +
            "values" +
            " (#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Long id);

    /**
     * 分页查询
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    List<Category> list(Integer type);
}
