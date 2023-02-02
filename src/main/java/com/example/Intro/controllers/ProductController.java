package com.example.Intro.controllers;

import com.example.Intro.models.Product;
import com.example.Intro.models.dtos.ProductDTO;
import com.example.Intro.services.ProductService;
import com.example.Intro.services.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController implements ProductServiceInterface {

    @Autowired
    ProductService productService;


    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody ProductDTO productDTO) {
        return productService.addNewProduct(productDTO);
    }

    @PatchMapping("/products-decrementing/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product decrementingQuantityOfProduct(@PathVariable Long id,@RequestParam Integer quantity) {
        return productService.decrementingQuantityOfProduct(id,quantity);
    }

    @GetMapping("/products-department/{department}")
    public List<Product> getAllProductsByDepartment(@PathVariable String department) {
        return productService.getAllProductsByDepartment(department);
    }

    @GetMapping("products-id/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


}
