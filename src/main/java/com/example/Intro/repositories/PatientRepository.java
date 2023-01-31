package com.example.Intro.repositories;

import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    @Query(value = "SELECT p FROM Patient p WHERE dateOfBirth BETWEEN :startDate AND :endDate")
    List<Patient> findAllPatientsBetweenDatesOfBirth(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

    @Query(value = "SELECT p FROM Patient p INNER JOIN Employee e ON e.employeeId = p.admittedBy WHERE e.department = :department")
    List<Patient> findAllPatientsByDepartmentWithDoctorInDepartment(@Param("department") String department);

    @Query(value = "SELECT p FROM Patient p INNER JOIN Employee e ON e.employeeId = p.admittedBy WHERE e.status = :status")
    List<Patient> findAllPatientsByDepartmentWithDoctorStatus(@Param("status") Status status);



}
