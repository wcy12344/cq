package org.example.cq.mapper;


import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.cq.model.dto.employee.EmployeePageQueryDTO;
import org.example.cq.model.entity.Employee;

@Mapper
public interface  EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    /**
     * 新增员工
     *
     * @param employee
     */
    @Insert("insert into employee " +
            "(username, name, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Employee employee);

    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
