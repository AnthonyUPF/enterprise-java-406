package com.example.Intro.services;

import com.example.Intro.models.Department;
import com.example.Intro.models.Product;
import com.example.Intro.repositories.DepartmentRepository;
import com.example.Intro.repositories.ProductRepository;
import com.example.Intro.services.interfaces.DepartmetnServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService implements DepartmetnServicesInterface {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addNewDepartment(String department) {
        if(!departmentRepository.findByDepartment(department).isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "There is a department whose name already exists in the database");
        }

        Department department1=new Department(department);

        return departmentRepository.save(department1);
    }

    @Override
    public Department deleteProductList(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The department id is not valid"));
        List<Product> productList=productRepository.findByDepartmentDepartment(department.getDepartment());
        productRepository.deleteAll(productList);
        departmentRepository.delete(department);
        return department;
    }


}
