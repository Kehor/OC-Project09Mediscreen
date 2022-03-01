package com.openclassrooms.Mediscreen.microservice;

import com.openclassrooms.Mediscreen.entity.Appointment;
import com.openclassrooms.Mediscreen.entity.Login;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Praticien;

import java.util.Date;

public interface SqlApiMicroservice {

    public Login login(String email);

    public Patient getpatientbyname(String prenom, String nom);


    public Patient getpatientbyemail(String email);

    public Patient getpatientbyid(Long id);

    public Patient savepatient(String jsonpatient);

    public Praticien getpraticienbyId(Long id);

    public Appointment saveappointment(Long id, Long patientid, Long praticienid, Date reservedAt);

}
