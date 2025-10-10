package com.example.demo.controller;

import com.example.demo.employee.Employee;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/home")
    public String display(){
        return  "helloworld!!!";
    }

    @GetMapping("/employees")
    public List<EmployeeEntity> getEmployeeList(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public EmployeeEntity create(@RequestBody EmployeeEntity employee){
        return employeeRepository.save(employee);
    }
    @GetMapping("/employees/optional/{id}")
    public Optional<EmployeeEntity> getEmployeeDetailsWithOptional(@PathVariable Long id){
        return employeeRepository.findById(id);
    }

    @GetMapping("/employees/{id}")
    public EmployeeEntity getEmployeeDetails(@PathVariable Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("no employee exist with the id = "+id));
    }
    
    @PutMapping("/employees/{id}")
    public EmployeeEntity updateEmployeeDetails(@PathVariable Long id, @RequestBody EmployeeEntity employee){
        EmployeeEntity updateData =   employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("no employee exist with the id = "+id));
        updateData.setName(employee.getName());
        updateData.setDepartment(employee.getDepartment());
        return employeeRepository.save(updateData);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeDetails(@PathVariable Long id){
        EmployeeEntity deleteData =   employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("no employee exist with the id = "+id));
        employeeRepository.delete(deleteData);
        return ResponseEntity.ok("successfully deleted");
    }




}
