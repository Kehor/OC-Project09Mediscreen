package com.openclassrooms.Mediscreen.proxy;

import com.openclassrooms.Mediscreen.entity.Appointment;
import com.openclassrooms.Mediscreen.entity.Login;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Praticien;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "microservice-SqlApi", url = "http://localhost:8081")
public interface SqlApiProxy {

    @PostMapping(value = "/login")
    Login login(@RequestParam String email);

    @PostMapping(value = "/patient/getbyname")
    Patient getpatientbyname(@RequestParam String prenom, @RequestParam String nom);

    @PostMapping(value = "/patient/getbyemail")
    Patient getpatientbyemail(@RequestParam String email);

    @PostMapping(value = "/patient/getbyid")
    Patient getpatientbyId(@RequestParam Long id);


    @PostMapping(value = "/patient/save")
    String savepatient(@RequestParam String jsonpatient);


    @PostMapping(value = "/praticien/getbyid")
    Praticien getpraticienbyId(@RequestParam Long id);

    @PostMapping(value = "/appointment/save")
    Appointment saveAppointement(@RequestParam Long id, @RequestParam Long praticienid, @RequestParam Long patientid, @RequestParam Date reservedAt);
}
