package org.example.cq.service.impl;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.PageResult;
import org.example.cq.constant.MessageConstant;
import org.example.cq.constant.StatusConstant;
import org.example.cq.mapper.EmployeeMapper;
import org.example.cq.model.dto.employee.EmployeeDTO;
import org.example.cq.model.dto.employee.EmployeeLoginDTO;
import org.example.cq.model.dto.employee.EmployeePageQueryDTO;
import org.example.cq.model.entity.Employee;
import org.example.cq.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
            throw new RuntimeException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        if(!emp.getPassword().equals(encryptPassword)) {
            throw new RuntimeException(MessageConstant.PASSWORD_ERROR);
        }

        if(emp.getStatus() == StatusConstant.DISABLE) {
            throw new RuntimeException(MessageConstant.ACCOUNT_LOCKED);
        }
        return emp;
    }

    @Override
    public void save(EmployeeDTO employeeDTO) {

    }

    @Override
    public PageResult<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        return null;
    }

    @Override
    public void startOrStop(Integer status, long id) {

    }

    @Override
    public Employee getById(long id) {
        return null;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {

    }
}
