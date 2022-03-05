package com.openclassrooms.Mediscreen.controller;

import com.openclassrooms.Mediscreen.entity.*;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import com.openclassrooms.Mediscreen.service.AppointementService;
import com.openclassrooms.Mediscreen.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class MediscreenController {


    @Autowired
    private SqlApiMicroservice sqlApiMicroservice;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointementService appointementService;
    private Logger logger = LoggerFactory.getLogger(MediscreenController.class);

    @GetMapping("/")
    public String home(Model model) {
        logger.info("Home : MediscreenHome");
        return "Home";
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginexit(Model model){
        return home(model);
    }

    @GetMapping(value = "/logout")
    public String logout(Model model){ return login(model); }

    @GetMapping(value = "/register")
    public String register(Model model){
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerexit(@RequestParam(name="email", required=true) String email, @RequestParam(name="prenom", required=true) String prenom, @RequestParam(name="nom", required=true) String nom, @RequestParam(name="dob", required=true) String dob, @RequestParam(name="sex", required=true) String sex, @RequestParam(name="address", required=false) String address, @RequestParam(name="phone", required=false) String phone, @RequestParam(name="pass", required=true) String pass, Model model){
        patientService.savePatient(email,pass,prenom,nom,dob,sex,address,phone);
        return login(model);
    }

    @GetMapping("/search")
    public String search(Model model) {
        logger.info("search : MediscreenSearch");
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("Praticien"))){
            Patient patient = new Patient();
            model.addAttribute("patient", patient);
        }else {
            List<Praticien> praticiens = sqlApiMicroservice.getAllPraticien();
            model.addAttribute("praticiens", praticiens);
        }
        return "search";
    }

    @PostMapping("/search")
    public String getpatientinfo(@RequestParam(name="prenom", required=true) String prenom,@RequestParam(name="nom", required=true) String nom,Model model) {
        logger.info("search : Mediscreen");
        Patient patient = sqlApiMicroservice.getpatientbyname(prenom,nom);
        model.addAttribute("patient", patient);
        return "search";
    }

    @PostMapping("/profile")
    public String Profil(@RequestParam(name="id", required=true) Long id, Model model) {
        logger.info("profile : Mediscreen");
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("Praticien"))){
            Patient patient = sqlApiMicroservice.getpatientbyid(id);
            List<Notes> notesList = patientService.getNotesByPatientid(id);
            notesList.add(new Notes((long)0,id, new Date(), new Date(),""));
            Test test = patientService.getTestDiabete(id);
            model.addAttribute("test", test);
            model.addAttribute("notesList", notesList);
            model.addAttribute("patient", patient);
        }else {
            Praticien praticien = sqlApiMicroservice.getpraticienbyId(id);
            Long uid = patientService.getpatientbyemail(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
            model.addAttribute("uid", uid);
            model.addAttribute("praticien", praticien);
        }
        return "profile";
    }

    @GetMapping("/myprofile")
    public String myprofile(Model model) {
        logger.info("profile : myprofile");
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("Praticien"))){
            Praticien praticien = sqlApiMicroservice.getpraticienbyemail(SecurityContextHolder.getContext().getAuthentication().getName());
            List<Appointment> appointments = appointementService.findAllByPraticien(praticien.getId());
            model.addAttribute("appointments", appointments);
            model.addAttribute("praticien", praticien);
        }else {
            Patient patient = patientService.getpatientbyemail(SecurityContextHolder.getContext().getAuthentication().getName());
            List<Appointment> appointments = appointementService.findAllByPatient(patient.getId());
            model.addAttribute("appointments", appointments);
            model.addAttribute("patient", patient);
        }
        return "profile";
    }

    @PostMapping("/editprofile")
    public String editProfil(@RequestParam(name="email", required=true) String email, @RequestParam(name="prenom", required=true) String prenom, @RequestParam(name="nom", required=true) String nom, @RequestParam(name="dob", required=false) String dob, @RequestParam(name="sex", required=true) String sex, @RequestParam(name="address", required=true) String address, @RequestParam(name="phone", required=true) String phone, @RequestParam(name="pass", required=false) String pass, Model model) {
        logger.info("editprofile : Mediscreen");

        Patient patient = patientService.savePatient(email,pass,prenom,nom,dob,sex,address,phone);
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("Patient"))){
            return myprofile(model);
        }
        return Profil(patient.getId(),model);
    }

    @PostMapping("/editnotes")
    public String editnotes( @RequestParam(name="notesid", required=true) Long id,@RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="practitionnerNotesRecommandation", required=true) String practitionnerNotesRecommandation, Model model) {
        logger.info("editnotes : Mediscreen");
        patientService.editnotes(id,patientid,practitionnerNotesRecommandation);
        return Profil(patientid,model);
    }

    @PostMapping("/appointment/save")
    public String editAppointment(@RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="praticienid", required=true) Long praticienid, @RequestParam(name="reservedAt", required=true) String reservedAt, Model model) {
        logger.info("appointment : editAppointment");
        Appointment appointment = appointementService.save(null,patientid,praticienid,reservedAt);
        return Profil(praticienid,model);
    }

    @PostMapping("/appointment/delete")
    public String deleteAppointment(@RequestParam(name="id", required=true) Long id,@RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="praticienid", required=true) Long praticienid, Model model) {
        logger.info("appointment : deleteAppointment");
        if(appointementService.delete(id,praticienid,patientid)){
            logger.info("appointment : delete Complete");
        }else {
            logger.info("appointment : delete Fail");
        }
        Praticien praticien = sqlApiMicroservice.getpraticienbyId(praticienid);
        return myprofile(model);
    }

}
