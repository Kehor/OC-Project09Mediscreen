package com.openclassrooms.Mediscreen.service;



import com.openclassrooms.Mediscreen.entity.Appointment;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Praticien;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class AppointementService {

    @Autowired
    private SqlApiMicroservice sqlApiMicroservice;

    public Appointment save(Long id, Long patientid, Long praticienid, String reservedAt){
        if(id == null) id = (long) 0;
        Appointment appointment = sqlApiMicroservice.saveappointment(id,patientid,praticienid,reservedAt);
        return appointment;
    }

    public List<Appointment> findAllByPatient(Long id){
        List<Appointment> appointment = new ArrayList<Appointment>();
        appointment = sqlApiMicroservice.findAllByPatient(id);
        return appointment;
    }

    public List<Appointment> findAllByPraticien(Long id){
        List<Appointment> appointment = new ArrayList<Appointment>();
        appointment = sqlApiMicroservice.findAllByPraticien(id);
        return appointment;
    }

    // true if AppointmentDto found and deleted
    public Boolean delete(Long id, Long praticienid, Long patientid){
        if(id == null) id = (long) 0;
        Boolean delete = sqlApiMicroservice.deleteByid(id,praticienid,patientid);
        return delete;
    }
}
