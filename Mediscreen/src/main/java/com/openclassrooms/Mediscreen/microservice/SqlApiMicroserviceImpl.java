package com.openclassrooms.Mediscreen.microservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.Mediscreen.dto.AppointmentDto;
import com.openclassrooms.Mediscreen.entity.*;
import com.openclassrooms.Mediscreen.proxy.SqlApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.util.ArrayList;
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
    public Praticien getpraticienbyemail(String email){
        Praticien praticien = sqlApiProxy.getpraticienbyemail(email);

        return praticien;
    }

    @Override
    public List<Praticien> getAllPraticien(){
        List<Praticien> praticiens = sqlApiProxy.getAllPraticien();

        return praticiens;
    }

    @Override
    public Appointment saveappointment(Long id, Long patientid, Long praticienid, String reservedAt){
        Appointment appointment = sqlApiProxy.saveAppointement(id,praticienid,patientid,reservedAt);
        return appointment;
    }

    @Override
    public List<Appointment> findAllByPatient(Long id){
        List<AppointmentDto> appointments = sqlApiProxy.findAllByPatient(id);
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentDto appointment: appointments) {
            appointmentList.add(new Appointment(appointment.getId(),sqlApiProxy.getpatientbyId(appointment.getPatientId()),sqlApiProxy.getpraticienbyId(appointment.getPraticienId()),appointment.getReservedAt(),appointment.getCreatedAt()));
        }
        return appointmentList;
    }

    @Override
    public List<Appointment> findAllByPraticien(Long id){
        List<AppointmentDto> appointments = sqlApiProxy.findAllByPraticien(id);
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentDto appointment: appointments) {
            appointmentList.add(new Appointment(appointment.getId(),sqlApiProxy.getpatientbyId(appointment.getPatientId()),sqlApiProxy.getpraticienbyId(appointment.getPraticienId()),appointment.getReservedAt(),appointment.getCreatedAt()));
        }
        return appointmentList;
    }

    @Override
    public Boolean deleteByid(Long id, Long praticienid, Long patientid){
        Boolean delete = sqlApiProxy.deleteByid(id,praticienid,patientid);
        return delete;
    }

    @Override
    public Test getTest(Long id){
        Test test = sqlApiProxy.getTest(id);
        return test;
    }
}
