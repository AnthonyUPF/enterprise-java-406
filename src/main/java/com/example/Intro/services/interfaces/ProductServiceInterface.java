package com.example.Intro.services.interfaces;

import com.example.Intro.models.Department;
import com.example.Intro.models.Product;
import com.example.Intro.models.dtos.ProductDTO;

import java.util.List;

public interface ProductServiceInterface{

    List<Product> getAllProducts();

    //2-Create a route for adding new products (validate the payload format)
    Product addNewProduct(ProductDTO productDTO);

    /*3-Create a route for decrementing the quantity of a product by an amount specified in a parameter.
    Throw a custom error and respond with an appropriate response code if the specified amount is less than the quantity of the product in stock.
     */
    Product decrementingQuantityOfProduct(Long id, Integer quantity);

    //4-Create a route to get all products by department. Return all products if the department param is empty/null.
    List<Product> getAllProductsByDepartment(String department);

    //5-Create a route to get a product by id. Throw an error and respond with an appropriate response code if the id does not exist.
    Product getProductById(Long id);



}
