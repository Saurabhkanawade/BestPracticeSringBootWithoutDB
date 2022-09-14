package com.example.employeproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeEntity {

    private Long employeeId;
    private String firstName;
    private String lastName;

}
