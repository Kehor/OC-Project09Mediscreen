package com.openclassrooms.Mediscreen.controller;

import com.openclassrooms.Mediscreen.entity.*;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import com.openclassrooms.Mediscreen.service.AppointementService;
import com.openclassrooms.Mediscreen.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDateTime;
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
    public String registerexit(@RequestParam(name="name", required=false) String name, @RequestParam(name="email", required=false) String email, @RequestParam(name="password", required=false) String password, @RequestParam(name="iban", required=false) String iban, Model model){
        return login(model);
    }

    @GetMapping("/search")
    public String search(Model model) {
        logger.info("search : MediscreenSearch");
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "search";
    }

    @PostMapping("/search")
    public String getpatientinfo(@RequestParam(name="prenom", required=true) String prenom,@RequestParam(name="nom", required=true) String nom,Model model) {
        logger.info("search : MediscreenSearch");
        Patient patient = sqlApiMicroservice.getpatientbyname(prenom,nom);
        model.addAttribute("patient", patient);
        return "search";
    }

    @GetMapping("/profile")
    public String Profil(@RequestParam(name="patientid", required=true) Long id, Model model) {
        logger.info("profile : MediscreenHome");
        Patient patient = sqlApiMicroservice.getpatientbyid(id);
        List<Notes> notesList = patientService.getNotesByPatientid(id);
        List<Appointment> appointmentList = (List<Appointment>) new Appointment();
        notesList.add(new Notes((long)0,id, new Date(), new Date(),""));
        model.addAttribute("notesList", notesList);
        model.addAttribute("patient", patient);
        return "profile";
    }

    @PostMapping("/editprofile")
    public String editProfil(@RequestParam(name="email", required=true) String email, @RequestParam(name="prenom", required=true) String prenom, @RequestParam(name="nom", required=true) String nom, @RequestParam(name="dob", required=false) String dob, @RequestParam(name="sex", required=true) String sex, @RequestParam(name="address", required=true) String address, @RequestParam(name="phone", required=true) String phone, @RequestParam(name="pass", required=false) String pass, @RequestParam(name="family", required=false) Test family, Model model) {
        logger.info("editprofile : Mediscreen");

        Patient patient = patientService.savePatient(email,pass,prenom,nom,dob,sex,address,phone,family);
        return Profil(patient.getId(),model);
    }

    @PostMapping("/editnotes")
    public String editnotes( @RequestParam(name="notesid", required=true) Long id,@RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="practitionnerNotesRecommandation", required=true) String practitionnerNotesRecommandation, Model model) {
        logger.info("editnotes : Mediscreen");
        patientService.editnotes(id,patientid,practitionnerNotesRecommandation);
        return Profil(patientid,model);
    }

    @GetMapping("/profile/praticien")
    public String Profilpraticien(@RequestParam(name="praticienid", required=true) Long praticienid, Model model) {
        logger.info("profile/praticien : Mediscreen");
        //SecurityContextHolder.getContext().getAuthentication().getName()
        Praticien praticien = sqlApiMicroservice.getpraticienbyId(praticienid);
        //Appointment = new Appointment((long)0,))
        model.addAttribute("praticien", praticien);
        return "praticien";
    }

    @GetMapping("/editappointment")
    public String appointment(@RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="praticienid", required=true) Long praticienid, Model model) {

        return "appointment";
    }
}
