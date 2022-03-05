package com.openclassrooms.Mediscreen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.Mediscreen.entity.Notes;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Test;
import com.openclassrooms.Mediscreen.microservice.NoSqlApiMicroservice;
import com.openclassrooms.Mediscreen.microservice.SqlApiMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PatientService {

    @Autowired
    private SqlApiMicroservice sqlApiMicroservice;
    @Autowired
    private NoSqlApiMicroservice noSqlApiMicroservice;

    public Patient getpatientbyemail(String email){
        Patient patient = sqlApiMicroservice.getpatientbyemail(email);
        return patient;
    }

    public Patient savePatient(String email, String password, String prenom, String nom, String dob, String sex, String adrress, String phone){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        ObjectMapper Obj = new ObjectMapper();
        String jsonpatient = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.FRANCE);
        Date dobdate = null;
        try {
            dobdate = formatter.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Patient patient = new Patient((long) 0,email,encodedPassword,prenom,nom,dobdate,sex,adrress,phone,null,null);
        try {
            jsonpatient = Obj.writeValueAsString(patient);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        patient = sqlApiMicroservice.savepatient(jsonpatient);
        return patient;
    }

    public List<Notes> getNotesByPatientid(Long id){
        List<Notes> notesList = noSqlApiMicroservice.getNotesbyPatientid(id);
        return notesList;
    }

    public Notes editnotes(Long id, Long patientid, String practitionnerNotesRecommandation){
        Notes notes = noSqlApiMicroservice.saveNotes(id,patientid,practitionnerNotesRecommandation);
        return notes;
    }

    public Test getTestDiabete(Long id){
        Test test = sqlApiMicroservice.getTest(id);
        return test;
    }
}
