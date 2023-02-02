package com.example.Intro.services;

import com.example.Intro.models.Department;
import com.example.Intro.models.Product;
import com.example.Intro.models.dtos.ProductDTO;
import com.example.Intro.repositories.DepartmentRepository;
import com.example.Intro.repositories.ProductRepository;
import com.example.Intro.services.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(ProductDTO productDTO) {
        Department department = departmentRepository.findById(productDTO.getDepartmentId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "The department id is not valid to add the product"));
        Product product = new Product(department,productDTO.getName(),productDTO.getQuantity());
        return  productRepository.save(product);
    }

    @Override
    public Product decrementingQuantityOfProduct(Long id, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "The product id is not valid to decrement its quantity"));

        if(quantity> product.getQuantity()){
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The quantity to decrement is greater than the current quantity of the product");
        }

        product.setQuantity(product.getQuantity()-quantity);
        return productRepository.save(product);
    }



    @Override
    public List<Product> getAllProductsByDepartment(String department) {
        if(department!=null ){
            return productRepository.findByDepartmentDepartment(department);
        }
        return getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return  productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                "The product id is not valid"));
    }


}
