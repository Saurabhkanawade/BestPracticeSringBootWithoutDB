package com.example.employeproject.service;

import com.example.employeproject.entity.EmployeeEntity;
import com.example.employeproject.model.EmployeeRequest;
import com.example.employeproject.model.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    List<EmployeeEntity> employeeList;

    {
        new ArrayList<>();
        employeeList.add(new EmployeeEntity(1L, "saurabh", "kanawade"));
    }

    public EmployeeEntity getEmployee(Long employeeId) {
        return employeeList.stream().filter(e -> e.getEmployeeId() == employeeId).findFirst().get();
    }

    public List<EmployeeEntity> getAllEmployee() {
        return employeeList;
    }


    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeRequest.getFirstName());
        employeeEntity.setLastName(employeeRequest.getLastName());

        EmployeeResponse employeeResponse = new EmployeeResponse();
        long id = (long) (Math.random() * 20);
        employeeEntity.setEmployeeId((long) id);
        employeeResponse.setEmployeeId(employeeEntity.getEmployeeId());
        employeeList.add(employeeEntity);

        return employeeResponse;
    }

    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeList.stream().map(
                e -> {
                    if (e.getEmployeeId() == employeeId) {
                        e.setFirstName(employeeRequest.getFirstName());
                        e.setLastName(employeeRequest.getLastName());
                        employeeResponse.setEmployeeId(e.getEmployeeId());
                    }
                    return e;
                }).collect(Collectors.toList());
        return employeeResponse;
    }

    public void deleteEmployee(Long employeeId) {
        employeeList.removeIf(e -> e.getEmployeeId() == employeeId);
    }

}
