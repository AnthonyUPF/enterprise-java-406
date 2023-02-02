package com.example.Intro.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NotNull
    @NotEmpty
    private String department;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    List<Product> productList=new ArrayList<>();

    public Department() {
    }

    public Department(String department) {
        this.department = department;
    }

    public Long getDepartmentId() {
        return departmentId;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
