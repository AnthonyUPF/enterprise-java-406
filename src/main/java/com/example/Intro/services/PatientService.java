package com.example.Intro.services;

import com.example.Intro.controllers.dtos.PatientDTO;
import com.example.Intro.models.Employee;
import com.example.Intro.models.Patient;
import com.example.Intro.models.Status;
import com.example.Intro.repositories.EmployeeRepository;
import com.example.Intro.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        if(patientDTO!=null) {
            Employee employee = employeeRepository.findById(patientDTO.getEmployeeId()).get();
            Patient patient = new Patient(patientDTO.getName(), patientDTO.getDateOfBirth(), employee);
            return patientRepository.save(patient);
        }else{
            return null;
        }
    }

    //5 Create a route to update patient’s information (the user should be able to update any patient information through this route).
    public void updatePatientsInformation(Integer id, String name, LocalDate dateOfBirth, Integer doctorId) {
        if(id!=null && patientRepository.findById(id).isPresent()){
            Patient patient = patientRepository.findById(id).get();

            if(name!=null){
                patient.setName(name);
            }
            if(dateOfBirth!=null){
                patient.setDateOfBirth(dateOfBirth);
            }
            if(doctorId!=null && employeeRepository.findById(doctorId).isPresent()){
                Employee doctor = employeeRepository.findById(doctorId).get();
                patient.setAdmittedBy(doctor);
            }

            patientRepository.save(patient);
        }
    }


}
