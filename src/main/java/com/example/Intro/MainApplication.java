package com.example.Intro;


import com.example.Intro.models.Department;
import com.example.Intro.models.Product;
import com.example.Intro.repositories.DepartmentRepository;
import com.example.Intro.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;



@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {



		departmentRepository.saveAll(Arrays.asList(
				new	Department("tools"),
				new	Department("edible plants"),
				new Department("non-edible plants"),
				new Department("edible seeds"),
				new Department("non-edible seeds"),
				new Department("miscellaneous")
		));


		productRepository.saveAll(Arrays.asList(
				new Product(departmentRepository.findById(1L).get(),"small shovel",50),
				new Product(departmentRepository.findById(1L).get(),"large shovel",150),
				new Product(departmentRepository.findById(2L).get(),"apple tree sapling",10),
				new Product(departmentRepository.findById(4L).get(),"assorted root vegetable seed packet",2000),
				new Product(departmentRepository.findById(5L).get(),"geranium seed packet",1000),
				new Product(departmentRepository.findById(2L).get(),"sprouted carrots",200),
				new Product(departmentRepository.findById(6L).get(),"large brim gardening hat",25)
		));

	}
}
