package com.example.Intro.repositories;


import com.example.Intro.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByDepartmentDepartment(String department);

    Product findByName(String name);
}
