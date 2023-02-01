package com.example.Intro.services;

import com.example.Intro.controllers.dtos.PatientDTO;
import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.EmployeeRepository;
import com.example.Intro.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    //Lab402
    //5 Create a route to get all patients.
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    //6 Create a route to get a patient by patient_id.
    public Patient getPatientsById(@PathVariable Integer id) {
        return patientRepository.findById(id).get();
    }

    //7 Create a route to get patients date of birth within a specified range.
    public List<Patient> getAllPatientsByRangeOfBirth(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return patientRepository.findAllPatientsBetweenDatesOfBirth(startDate,endDate);
    }

    //8 Create a route to get patients by the department that their admitting doctor is in (For example, get all patients admitted by a doctor in cardiology).
    public List<Patient> getAllPatientsByDepartmentWithDoctorInSameDepartment(@PathVariable String department) {
        return patientRepository.findAllPatientsByDepartmentWithDoctorInDepartment(department);
    }

    //9 Create a route to get all patients with a doctor whose status is OFF (ON or  ON_CALL).
    public List<Patient> getAllPatientsByDepartmentWithDoctorInSomeStatus(@PathVariable Status status) {
        return patientRepository.findAllPatientsByDepartmentWithDoctorStatus(status);
    }

    //Lab404
    //1 Create a route to add a new patient.
    public Patient createNewPatient(PatientDTO patientDTO) {

        if(!patientRepository.findByName(patientDTO.getName()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "There is a patient whose name already exists in the database");
        }

        Employee employee = employeeRepository.findById(patientDTO.getEmployeeId()).get();
        Patient patient = new Patient(patientDTO.getName(), patientDTO.getDateOfBirth(), employee);
        return patientRepository.save(patient);
    }


    //5 Create a route to update patient’s information (the user should be able to update any patient information through this route).
    public Patient updatePatientsInformation(Integer id, PatientDTO newPatient) {

          Patient patient=patientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The product you are looking for doesn't exist in the database"));


            if(newPatient.getName() !=null){
                patient.setName(newPatient.getName() );
            }
            if(newPatient.getDateOfBirth() !=null){
                patient.setDateOfBirth(newPatient.getDateOfBirth());
            }
            if(newPatient.getEmployeeId()  !=null && employeeRepository.findById(newPatient.getEmployeeId() ).isPresent()){
                Employee doctor = employeeRepository.findById(newPatient.getEmployeeId() ).get();
                patient.setAdmittedBy(doctor);
            }

           return patientRepository.save(patient);

    }


}
