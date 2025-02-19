package org.example.cq.model.dto.employee;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

}
