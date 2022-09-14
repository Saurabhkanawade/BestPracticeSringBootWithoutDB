package com.example.employeproject.service;

import com.example.employeproject.model.EmployeeEntity;
import com.example.employeproject.model.EmployeeRequest;
import com.example.employeproject.model.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private List<EmployeeEntity> employeeList = new ArrayList<>();
    {
        employeeList.add(new EmployeeEntity(1L, "saurabh", "kanawade"));
    }

//    private List<EmployeeEntity> employeeList;
//    private EmployeeService(){
//       employeeList =List.of(
//                new EmployeeEntity(1L,"Saurabh","Kanawade")
//        );
//    }


    //    get the employee by id
    public EmployeeEntity getEmployee(Long employeeId) {
        return employeeList.stream().filter(e -> e.getEmployeeId() == employeeId).findFirst().get();
    }

    //    get all employees from the list
    public List<EmployeeEntity> getAllEmployee() {
        return employeeList;
    }


    //    create new employe into the list
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeRequest.getFirstName());
        employeeEntity.setLastName(employeeRequest.getLastName());

        EmployeeResponse employeeResponse = new EmployeeResponse();
//        Random random = new Random();
        long id = (long) (Math.random() * 20);
        employeeEntity.setEmployeeId((long) id);
        employeeResponse.setEmployeeId(employeeEntity.getEmployeeId());

//        added into the list
        employeeList.add(employeeEntity);

        return employeeResponse;
    }

    //    update the exiting employee
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse=new EmployeeResponse();
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

//        EmployeeEntity list = null;
//        for (EmployeeEntity e :list) {
//            if (employeeId == e.getEmployeeId()) {
//                e.setFirstName(list.getFirstName());
//                e.setLastName(list.getLastName());
//                break;
//            }
//        }
//        return employee;
//    }

    //    delete the employee by id
    public void deleteEmployee(Long employeeId) {
        employeeList.removeIf(e -> e.getEmployeeId() == employeeId);
    }

}
