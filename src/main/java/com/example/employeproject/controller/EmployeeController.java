package com.example.employeproject.controller;

import com.example.employeproject.entity.EmployeeEntity;
import com.example.employeproject.model.EmployeeRequest;
import com.example.employeproject.model.EmployeeResponse;
import com.example.employeproject.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeEntity>> getEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping(path = "/employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);

    }

    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        if (employeeResponse != null) {
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employeeResponse, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "employees/{employeeId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long employeeId ,
                                                                    @RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId,employeeRequest),HttpStatus.OK);
    }

    @DeleteMapping(path = "/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body("The Employee is Deleted where employeeId=" + employeeId);
    }
}
