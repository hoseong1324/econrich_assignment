package com.econrich.assignment.employees.repo;

import com.econrich.assignment.employees.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query("select e from Employees e " +
            "where e.employeeId = :employeeId")
    Optional<Employees> findByEmployeeId(@Param("employeeId") int employeeId);
}
