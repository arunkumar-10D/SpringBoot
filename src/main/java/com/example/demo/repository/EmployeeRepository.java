package com.example.demo.repository;

import com.example.demo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByUsername(String username);
    List<EmployeeEntity> findByDepartment(String department);

    @Query(nativeQuery = true,
    value = "SELECT * FROM employee WHERE department =:department AND name =:name")
    List<EmployeeEntity> findByDepartmentAndName(@Param("department") String department,
                                                 @Param("name") String name);
}
