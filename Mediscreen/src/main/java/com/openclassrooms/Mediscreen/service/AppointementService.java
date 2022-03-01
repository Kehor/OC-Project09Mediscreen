package com.openclassrooms.Mediscreen.service;



import com.openclassrooms.Mediscreen.entity.Appointment;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AppointementService {

    @Autowired
    private SqlApiMicroservice sqlApiMicroservice;

    public Appointment save(Long id, Long patientid, Long praticienid, Date reservedAt){
        if(id == null) id = (long) 0;
        Appointment appointment = sqlApiMicroservice.saveappointment(id,patientid,praticienid,reservedAt);
        return appointment;
    }

}
