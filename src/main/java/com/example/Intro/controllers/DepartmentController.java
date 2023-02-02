package com.example.Intro.controllers;

import com.example.Intro.models.Department;
import com.example.Intro.services.DepartmentService;
import com.example.Intro.services.interfaces.DepartmetnServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.List;

@RestController
public class DepartmentController implements DepartmetnServicesInterface {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    @ResponseStatus(HttpStatus.OK)
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments/{department}")
    @ResponseStatus(HttpStatus.CREATED)
    public Department addNewDepartment(@PathVariable String department) {
        return departmentService.addNewDepartment(department);
    }

    @DeleteMapping("/departments-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Department deleteProductList(@PathVariable Long id) {
        return departmentService.deleteProductList(id);
    }
}
