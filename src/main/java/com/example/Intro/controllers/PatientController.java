package com.example.Intro.controllers;


import com.example.Intro.controllers.dtos.PatientDTO;
import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.PatientRepository;
import com.example.Intro.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    //Lab402
    //5 Create a route to get all patients.
    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    //6 Create a route to get a patient by patient_id.
    @GetMapping("/patients-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientsById(@PathVariable Integer id) {
        return patientService.getPatientsById(id);
    }

    //7 Create a route to get patients date of birth within a specified range.
    @GetMapping("/patients-range-birth/{startDate}&{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatientsByRangeOfBirth(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return patientService.getAllPatientsByRangeOfBirth(startDate,endDate);
    }

    //8 Create a route to get patients by the department that their admitting doctor is in (For example, get all patients admitted by a doctor in cardiology).
    @GetMapping("/patients-doctors-departments/{department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatientsByDepartmentWithDoctorInSameDepartment(@PathVariable String department) {
        return patientService.getAllPatientsByDepartmentWithDoctorInSameDepartment(department);
    }

     //9 Create a route to get all patients with a doctor whose status is OFF (ON or  ON_CALL).
    @GetMapping("/patients-doctors-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatientsByDepartmentWithDoctorInSomeStatus(@PathVariable Status status) {
        return patientService.getAllPatientsByDepartmentWithDoctorInSomeStatus(status);
    }


    //Lab404
    //1 Create a route to add a new patient.
    @PostMapping("/patients/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createNewPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createNewPatient(patientDTO);

    }

    //5 Create a route to update patient’s information (the user should be able to update any patient information through this route).
    @PutMapping("/patients-information/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Patient updatePatientInformation(@PathVariable Integer id, @RequestBody PatientDTO patient){
        return patientService.updatePatientsInformation(id,patient);
    }




}
