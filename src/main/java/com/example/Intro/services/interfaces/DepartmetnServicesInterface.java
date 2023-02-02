package com.example.Intro.services.interfaces;

import com.example.Intro.models.Department;
import com.example.Intro.models.Product;

import java.util.List;

public interface DepartmetnServicesInterface {


    List<Department> getAllDepartments();

    //6-Create a route to add a new department (validate the payload format)
    Department addNewDepartment(String department);

    //7-Create a DELETE route to delete products from the product list by id.
    Department deleteProductList(Long id);
}
