package com.openclassrooms.MediscreenSqlApi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public Patient findOneByEmail(String email) {
        Patient patient = null;
        try {
            patient = patientRepository.findOneByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return patient;
    }

    public Optional<Patient> findOneById(Long id) {
        Optional<Patient> patient = null;
        try {
            patient = patientRepository.findOneById(id);
        } catch (Exception e) {
            throw e;
        }
        return patient;
    }

    public Patient saveJsonPatient(String jsonpatient){
        Patient patient = new Patient();
        Patient newpatient= new Patient();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            patient = objectMapper.readValue(jsonpatient, new TypeReference<Patient>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        newpatient = findOneByEmail(patient.getEmail());
        if(!patient.getPassword().isEmpty())newpatient.setPassword(patient.getPassword());
        if(!patient.getPrenom().isEmpty())newpatient.setPrenom(patient.getPrenom());
        if(!patient.getNom().isEmpty())newpatient.setNom(patient.getNom());
        if(patient.getDob() != null)newpatient.setDob(patient.getDob());
        if(!patient.getSex().isEmpty())newpatient.setSex(patient.getSex());
        if(!patient.getAddress().isEmpty())newpatient.setAddress(patient.getAddress());
        if(!patient.getPhone().isEmpty())newpatient.setPhone(patient.getPhone());
        if(patient.getFamily() != null)newpatient.setFamily(patient.getFamily());
        newpatient = patientRepository.save(newpatient);

        return newpatient;
    }


}
