package com.openclassrooms.Mediscreen.microservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.Mediscreen.entity.Appointment;
import com.openclassrooms.Mediscreen.entity.Login;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Praticien;
import com.openclassrooms.Mediscreen.proxy.SqlApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class SqlApiMicroserviceImpl implements SqlApiMicroservice {
    @Autowired
    private SqlApiProxy sqlApiProxy;

    @Override
    public Login login(String email){
    Login login = sqlApiProxy.login(email);

    return login;
    }

    @Override
    public Patient getpatientbyname(String prenom, String nom){
        Patient patient = sqlApiProxy.getpatientbyname(prenom,nom);

        return patient;
    }

    @Override
    public Patient getpatientbyemail(String email){
        Patient patient = sqlApiProxy.getpatientbyemail(email);

        return patient;
    }

    @Override
    public Patient getpatientbyid(Long id){
        Patient patient = sqlApiProxy.getpatientbyId(id);

        return patient;
    }

    @Override
    public Patient savepatient(String jsonpatient){
        Patient patient = new Patient();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = sqlApiProxy.savepatient(jsonpatient);

        try {
            patient = objectMapper.readValue(json, new TypeReference<Patient>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public Praticien getpraticienbyId(Long id){
        Praticien praticien = sqlApiProxy.getpraticienbyId(id);
        return praticien;
    }

    @Override
    public Appointment saveappointment(Long id, Long patientid, Long praticienid, Date reservedAt){
        Appointment appointment = sqlApiProxy.saveAppointement(id,praticienid,patientid,reservedAt);
        return appointment;
    }
}
