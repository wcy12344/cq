package org.example.cq.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.Result;
import org.example.cq.model.dto.employee.EmployeeDTO;
import org.example.cq.model.dto.employee.EmployeeLoginDTO;
import org.example.cq.model.entity.Employee;
import org.example.cq.model.vo.EmployeeLoginVO;
import org.example.cq.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource

    private EmployeeService employeeService;

    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeAddRequest) {
        log.info("新增员工: {}", employeeAddRequest);
//        Employee employee = new Employee();
//        BeanUtils.copyProperties(employeeAddRequest, employee);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser(StpUtil.getLoginIdAsLong());
//        employeeService.save(employee);
        return Result.success("成功");
    }

    /**
     * 员工登录
     *
     * @params employeeLoginDTO
     * @return EmployeeLoginVO
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("登录信息:{}", employeeLoginDTO);
        Employee emp = employeeService.login(employeeLoginDTO);
        StpUtil.login(emp.getId());
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(emp.getId())
                .name(emp.getName())
                .token(StpUtil.getTokenValue())
                .userName(emp.getUsername())
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 员工退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("退出成功");
    }
}
