package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  name;
    private String department;

    public EmployeeEntity(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
}
