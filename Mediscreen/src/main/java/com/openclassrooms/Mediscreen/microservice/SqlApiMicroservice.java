package com.openclassrooms.Mediscreen.microservice;

import com.openclassrooms.Mediscreen.dto.AppointmentDto;
import com.openclassrooms.Mediscreen.entity.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface SqlApiMicroservice {

    public Login login(String email);

    public Patient getpatientbyname(String prenom, String nom);

    public Patient getpatientbyemail(String email);

    public Patient getpatientbyid(Long id);

    public Patient savepatient(String jsonpatient);

    public Praticien getpraticienbyId(Long id);

    public Praticien getpraticienbyemail(String email);

    public List<Praticien> getAllPraticien();

    public Appointment saveappointment(Long id, Long patientid, Long praticienid, String reservedAt);

    public List<Appointment> findAllByPatient(Long id);

    public List<Appointment> findAllByPraticien(Long id);

    public Boolean deleteByid(Long id, Long praticienid, Long patientid);

    public Test getTest(Long id);
}
