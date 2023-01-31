package com.example.Intro.services;

import com.example.Intro.controllers.dtos.PatientDTO;
import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    //Lab402
    //1 Create a route to get all doctors.
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //2 Create a route to get a doctor by employee_id.
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id).get();
    }


    //3 Create a route to get doctors by status.
    public List<Employee> getAllEmployeesByStatus(@PathVariable Status status) {
        return employeeRepository.findByStatus(status);
    }


    //4 Create a route to get doctors by department.
    public List<Employee> getAllEmployeesByDepartment(@PathVariable String department) {
        return employeeRepository.findByDepartment(department);
    }

    //Lab404
    //2 Create a route to add a new doctor.
    public Employee createNewEmployee(Employee employee) {
        if(employee!=null){
            return employeeRepository.save(employee);
        }else{
            return null;
        }
    }

    //3 Create a route to change a doctor’s status.
    public void updateEmployeeStatus(Integer id, Status status) {
        if(id!=null && employeeRepository.findById(id).isPresent() && status!=null) {
            Employee employee = employeeRepository.findById(id).get();
            employee.setStatus(status);
            employeeRepository.save(employee);
        }
    }

    //4 Create a route to update a doctor’s department.
    public void updateEmployeeDepartment(Integer id, String department) {
        if(id!=null && employeeRepository.findById(id).isPresent() && department!=null) {
            Employee employee = employeeRepository.findById(id).get();
            employee.setDepartment(department);
            employeeRepository.save(employee);
        }
    }

}
