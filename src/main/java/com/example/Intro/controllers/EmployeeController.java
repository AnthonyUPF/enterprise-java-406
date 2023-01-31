package com.example.Intro.controllers;



import com.example.Intro.controllers.dtos.PatientDTO;
import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.EmployeeRepository;
import com.example.Intro.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    //Lab402
    //1 Create a route to get all doctors.
    @GetMapping("/doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //2 Create a route to get a doctor by employee_id.
    @GetMapping("/doctors-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }


    //3 Create a route to get doctors by status.
    @GetMapping("/doctors-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployeesByStatus(@PathVariable Status status) {
        return employeeService.getAllEmployeesByStatus(status);
    }


    //4 Create a route to get doctors by department.
    @GetMapping("/doctors-department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getAllEmployeesByDepartment(department);
    }

    //Lab404
    //2 Create a route to add a new doctor.
    @PostMapping("/doctors/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee employee) {
        return employeeService.createNewEmployee(employee);
    }

    //3 Create a route to change a doctor’s status.
    @PatchMapping("doctors-status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeStatus(@PathVariable Integer id, @RequestParam Status status) {
        employeeService.updateEmployeeStatus(id,status);
    }

    //4 Create a route to update a doctor’s department.
    @PatchMapping("doctors-department/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeDepartment(@PathVariable Integer id, @RequestParam String department) {
        employeeService.updateEmployeeDepartment(id,department);
    }




}
