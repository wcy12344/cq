package org.example.cq.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.PageResult;
import org.example.cq.model.dto.employee.EmployeeDTO;
import org.example.cq.model.dto.employee.EmployeeLoginDTO;
import org.example.cq.model.dto.employee.EmployeePageQueryDTO;
import org.example.cq.model.entity.Employee;
import org.example.cq.model.vo.EmployeeLoginVO;
import org.example.cq.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Resource

    private EmployeeService employeeService;

    @GetMapping("/page")
    public SaResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工信息: {}", employeePageQueryDTO);
        PageResult<Employee> pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return SaResult.data(pageResult);
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @PostMapping
    public SaResult save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return SaResult.ok("操作成功");
    }

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     */
    @PostMapping("/login")
    public SaResult login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("登录信息:{}", employeeLoginDTO);
        Employee emp = employeeService.login(employeeLoginDTO);
        StpUtil.login(emp.getId());
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(emp.getId())
                .name(emp.getName())
                .token(StpUtil.getTokenValue())
                .userName(emp.getUsername())
                .build();

        return SaResult.data(employeeLoginVO);
    }

    /**
     * 员工退出
     */
    @PostMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();

        return SaResult.ok("退出成功");
    }
}
