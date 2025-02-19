package org.example.cq.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.cq.common.ErrorCode;
import org.example.cq.common.Result;
import org.example.cq.constant.MessageConstant;
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
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Resource

    private EmployeeService employeeService;

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success("操作成功");
    }

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
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
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("退出成功");
    }
}
