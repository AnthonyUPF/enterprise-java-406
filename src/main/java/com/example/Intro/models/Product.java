package com.example.Intro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @NotNull
    @NotEmpty
    private String name;


    private Integer quantity;

    public Product() {
    }

    public Product(Department departmentId, String name, Integer quantity) {
        this.department = departmentId;
        this.name = name;
        this.quantity = quantity;
    }

    public Department getDepartment() {
        return department;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }
}
