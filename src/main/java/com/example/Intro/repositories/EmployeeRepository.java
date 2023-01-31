package com.example.Intro.repositories;


import com.example.Intro.models.Employee;
import com.example.Intro.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findByStatus(Enum status);

    List<Employee> findByDepartment(String department);


}
