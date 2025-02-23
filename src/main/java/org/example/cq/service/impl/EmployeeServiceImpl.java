package org.example.cq.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.PageResult;
import org.example.cq.constant.MessageConstant;
import org.example.cq.constant.PasswordConstant;
import org.example.cq.constant.StatusConstant;
import org.example.cq.exception.BaseException;
import org.example.cq.mapper.EmployeeMapper;
import org.example.cq.model.dto.employee.EmployeeDTO;
import org.example.cq.model.dto.employee.EmployeeLoginDTO;
import org.example.cq.model.dto.employee.EmployeePageQueryDTO;
import org.example.cq.model.entity.Employee;
import org.example.cq.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public static final String SALT = "wcy";
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String password = employeeLoginDTO.getPassword();
        String username = employeeLoginDTO.getUsername();

        Employee emp = employeeMapper.getByUsername(username);

        if(emp == null) {
            throw new BaseException(1, MessageConstant.ACCOUNT_NOT_FOUND);
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        if(!emp.getPassword().equals(encryptPassword)) {
            throw new BaseException(1,MessageConstant.PASSWORD_ERROR);
        }

        if(emp.getStatus() == StatusConstant.DISABLE) {
            throw new BaseException(1,MessageConstant.ACCOUNT_LOCKED);
        }
        return emp;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        // 拷贝对象属性
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setPassword(DigestUtils.md5DigestAsHex((SALT + PasswordConstant.DEFAULT_PASSWORD).getBytes()));
        employee.setStatus(StatusConstant.ENABLE);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(StpUtil.getLoginIdAsLong());
        employee.setUpdateUser(StpUtil.getLoginIdAsLong());
        try {
            employeeMapper.insert(employee);
        } catch (Exception e) {
            throw new RuntimeException(MessageConstant.ACCOUNT_ALREADY_EXIST);
        }
    }

    @Override
    public PageResult<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getCurrent(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult<>(total, records);
    }

    @Override
    public void startOrStop(Integer status, long id) {
        Employee employee = Employee.builder().id(id).status(status).build();
        employeeMapper.update(employee);
    }

    @Override
    public Employee  getById(long id) {
        Employee employee = employeeMapper.selectById(id);
        if(employee == null) {
            throw new BaseException(1, "员工不存在");
        }
        return employee;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtil.copyProperties(employeeDTO, employee);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(StpUtil.getLoginIdAsLong());
        employeeMapper.update(employee);
    }
}
