package com.example.Intro;


import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.EmployeeRepository;
import com.example.Intro.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;



@SpringBootApplication
public class IntroApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(IntroApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {
		employeeRepository.saveAll(Arrays.asList(
				new Employee(356712, "cardiology", "Alonso Flores", Status.ON_CALL),
				new Employee(564134, "immunology", "Sam Ortega", Status.ON),
				new Employee(761527, "cardiology", "German Ruiz", Status.OFF),
				new Employee(166552, "pulmonary", "Maria Lin", Status.ON),
				new Employee(156545, "orthopaedic", "Paolo Rodriguez", Status.ON_CALL),
				new Employee(172456, "psychiatric", "John Paul Armes", Status.OFF)
		));

		patientRepository.saveAll(Arrays.asList(
				new Patient("Jaime Jordan", LocalDate.of(1984, 3, 2),employeeRepository.findById(564134).get()),
				new Patient("Marian Garcia", LocalDate.of(1972, 1, 12),employeeRepository.findById(564134).get()),
				new Patient("Julia Dusterdieck", LocalDate.of(1954, 6, 11),employeeRepository.findById(356712).get()),
				new Patient( "Steve McDuck", LocalDate.of(1931, 11, 10),employeeRepository.findById(761527).get()),
				new Patient( "Marian Garcia", LocalDate.of(1999, 2, 15), employeeRepository.findById(172456).get())
		));

	}
}
