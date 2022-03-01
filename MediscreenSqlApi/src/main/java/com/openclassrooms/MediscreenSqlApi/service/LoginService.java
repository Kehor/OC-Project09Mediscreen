package com.openclassrooms.MediscreenSqlApi.service;

import com.openclassrooms.MediscreenSqlApi.dto.LoginDto;
import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Praticien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PraticienService praticienService;

    @Transactional(readOnly = true)
    public LoginDto loginByEmail(String email) {
        Optional<Praticien> praticien = null;
        Optional<Patient> patient = null;

        praticien = Optional.ofNullable(praticienService.findOneByEmail(email));
        patient = Optional.ofNullable(patientService.findOneByEmail(email));
        return new LoginDto(patient,praticien);
    }
}
