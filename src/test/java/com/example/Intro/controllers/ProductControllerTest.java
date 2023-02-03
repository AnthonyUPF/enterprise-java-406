package com.example.Intro.controllers;


import com.example.Intro.models.Product;
import com.example.Intro.models.dtos.ProductDTO;
import com.example.Intro.repositories.DepartmentRepository;
import com.example.Intro.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    Product product1;
    Product product2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        product1 = new Product(departmentRepository.findById(1L).get(), "Gafas", 200);
        product2 = new Product(departmentRepository.findById(2L).get(), "Campera", 49);
        productRepository.saveAll(List.of(product1, product2));
    }

    @Test
    public void  addNewProduct() throws Exception {
        ProductDTO product = new ProductDTO(1L, "Test", 100);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/products/new")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));

        assertEquals(product.getName(), productRepository.findByName(product.getName()).getName());
    }


    @Test
    public void decrementingQuantityOfProduct() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(patch("/products-decrementing/"+product1.getProductId())
                .param("quantity", "100")).andExpect(status().isOk()).andReturn();
        assertEquals(100, productRepository.findById(product1.getProductId()).get().getQuantity());
    }


    @Test
    public void decrementingQuantityOfProduct_Fail() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(patch("/products-decrementing/"+product1.getProductId())
                .param("quantity", "250")).andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("quantity to decrement is greater than the current quantity"));
    }

    @Test
    public void getAllProductsByDepartment() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(get("/products-department/"+(product1.getDepartment()).getDepartment() ))
                .andExpect(status().isOk()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());

        List<Product> productList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(productRepository.findByDepartmentDepartment("tools").size(), productList.size());
    }

    @Test
    public void getAllProductsByDepartment_fail() throws Exception {
        MvcResult  mvcResult = mockMvc.perform(get("/products-department/"+(product1.getDepartment()).getDepartment()+1 ))
                .andExpect(status().isOk()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());

        List<Product> productList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(0, productList.size());
    }


    @Test
    public void getProductById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/products-id/" + product1.getProductId() ))
                .andExpect(status().isOk()).andReturn();
        JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(json.get("name"), productRepository.findById(product1.getProductId() ).get().getName());
    }



    @Test
    public void getProductById_fail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/products-id/100" ))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("product id is not valid"));
    }



}
