package com.openclassrooms.Mediscreen.proxy;

import com.openclassrooms.Mediscreen.dto.AppointmentDto;
import com.openclassrooms.Mediscreen.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name = "microservice-SqlApi", url = "${proxy.sql}")
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

    @PostMapping(value = "/praticien/getbyemail")
    Praticien getpraticienbyemail(@RequestParam String email);

    @PostMapping(value = "/praticien/getall")
    List<Praticien> getAllPraticien();

    @PostMapping(value = "/appointment/save")
    Appointment saveAppointement(@RequestParam Long id, @RequestParam Long praticienid, @RequestParam Long patientid, @RequestParam String reservedAt);

    @PostMapping(value = "/appointment/patient")
    List<AppointmentDto> findAllByPatient(@RequestParam Long id);

    @PostMapping(value = "/appointment/praticien")
    List<AppointmentDto> findAllByPraticien(@RequestParam Long id);

    @PostMapping(value = "/appointment/delete")
    Boolean deleteByid(@RequestParam Long id, @RequestParam Long praticienid, @RequestParam Long patientid);

    @PostMapping(value = "/test")
    Test getTest(@RequestParam Long id);
}
