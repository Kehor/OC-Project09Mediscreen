package com.openclassrooms.MediscreenSqlApi;

import com.openclassrooms.MediscreenSqlApi.dto.LoginDto;
import com.openclassrooms.MediscreenSqlApi.entity.Appointment;
import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Praticien;
import com.openclassrooms.MediscreenSqlApi.entity.Test;
import com.openclassrooms.MediscreenSqlApi.repository.PatientRepository;
import com.openclassrooms.MediscreenSqlApi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class SqlApiController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PraticienService praticienService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger(SqlApiController.class);

    @GetMapping("/")
    public ResponseEntity  SqlApiHome() {
        logger.info("SqlApiController : SqlApiHome");
        return ResponseEntity.ok("SqlApi");
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestParam(name="email", required=true) String email) {
        logger.info("email : "+email);
        LoginDto loginDto = loginService.loginByEmail(email);
        logger.info("loginDto : "+loginDto.toString());
        return ResponseEntity.ok(loginDto);
    }

    @PostMapping("/patient/getbyname")
    public ResponseEntity getpatientbyname(@RequestParam(name="prenom", required=true) String prenom, @RequestParam(name="nom", required=true) String nom) {
        logger.info("getpatientbyname : "+prenom+nom);
        Patient patient = patientRepository.findOneByPrenomAndNom(prenom, nom);
        logger.info("getpatientbyname : "+patient);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patient/getbyid")
    public ResponseEntity getpatientbyid(@RequestParam(name="id", required=true) Long id) {
        logger.info("getpatientbyid : "+id);
        Optional<Patient> patient = patientService.findOneById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patient/getbyemail")
    public ResponseEntity getpatientbyemail(@RequestParam(name="email", required=true) String email) {
        logger.info("getpatientbyid : "+email);
        Patient patient = patientService.findOneByEmail(email);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/patient/save")
    public ResponseEntity savepatient(@RequestParam(name="jsonpatient", required=true) String jsonpatient){
        logger.info("savepatient : "+jsonpatient);
        Patient patient = patientService.saveJsonPatient(jsonpatient);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/praticien/getbyid")
    public ResponseEntity getpraticienbyid(@RequestParam(name="id", required=true) Long id) {
        logger.info("getpatientbyid : "+id);
        Optional<Praticien> praticien = praticienService.findOneById(id);
        return ResponseEntity.ok(praticien);
    }

    @PostMapping("/praticien/getbyemail")
    public ResponseEntity getpraticienbyemail(@RequestParam(name="email", required=true) String email) {
        logger.info("getpraticienbyemail : "+email);
        Praticien praticien = praticienService.findOneByEmail(email);
        return ResponseEntity.ok(praticien);
    }

    @PostMapping("/praticien/getall")
    public ResponseEntity getAllPraticien() {
        logger.info("getAllPraticien : ");
        List<Praticien> praticiens = praticienService.findAll();
        logger.info("getAllPraticien : "+praticiens);
        return ResponseEntity.ok(praticiens);
    }

    @PostMapping("/appointment/save")
    public ResponseEntity saveappointment(@RequestParam(name="id", required=true) Long id, @RequestParam(name="praticienid", required=true) Long praticienid, @RequestParam(name="patientid", required=true) Long patientid, @RequestParam(name="reservedAt", required=true) String reservedAt){
        logger.info("saveappointment : "+patientid+" "+praticienid);
        Appointment appointment = appointmentService.save(id,patientid,praticienid,reservedAt);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("/appointment/patient")
    public ResponseEntity findAllByPatient(@RequestParam(name="id", required=true) Long id){
        logger.info("findAllByPatient : "+id);
        List<Appointment> appointments = appointmentService.findAllByPatient(id);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/appointment/praticien")
    public ResponseEntity findAllByPraticien(@RequestParam(name="id", required=true) Long id){
        logger.info("findAllByPraticien : "+id);
        List<Appointment> appointments = appointmentService.findAllByPraticien(id);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/appointment/delete")
    public ResponseEntity deleteByid(@RequestParam(name="id", required=true) Long id,@RequestParam(name="praticienid", required=true) Long praticienid, @RequestParam(name="patientid", required=true) Long patientid){
        logger.info("deleteByid : "+id);
        Boolean delete = appointmentService.deleteByid(id,praticienid,patientid);
        return ResponseEntity.ok(delete);
    }

    @PostMapping("/test")
    public ResponseEntity getTest(@RequestParam(name="id", required=true) Long id){
        logger.info("test : "+id);
        Test test = testService.gettest(id);
        return ResponseEntity.ok(test);
    }
}
