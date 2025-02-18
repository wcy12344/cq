package org.example.cq.service;


import org.example.cq.common.PageResult;
import org.example.cq.model.dto.employee.EmployeeDTO;
import org.example.cq.model.dto.employee.EmployeeLoginDTO;
import org.example.cq.model.dto.employee.EmployeePageQueryDTO;
import org.example.cq.model.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 保存员工信息
     *
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工信息
     *
     * @param employeePageQueryDTO
     */
    PageResult<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用/禁用
     *
     * @param status
     * @param id
     *
     * @return
     */
    void startOrStop(Integer status, long id);

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee getById(long id);

    /**
     * 修改员工信息
     *
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

}
