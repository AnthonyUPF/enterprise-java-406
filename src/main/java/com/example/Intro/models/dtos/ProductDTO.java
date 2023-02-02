package com.example.Intro.models.dtos;

import com.example.Intro.models.Department;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotNull
    @NotEmpty
    private Long departmentId;


    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private Integer quantity;

    public ProductDTO(Long departmentId, String name, Integer quantity) {
        this.departmentId = departmentId;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
}
