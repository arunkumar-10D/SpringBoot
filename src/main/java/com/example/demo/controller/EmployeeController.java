package com.example.demo.controller;

import com.example.demo.employee.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @GetMapping("/home")
    public String display(){
        return  "helloworld!!!";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeeList(){
        return Arrays.asList(new Employee(1L,"arun","softwareDeveloper"),new Employee(2L,"kumar","R&D"));
    }




}
