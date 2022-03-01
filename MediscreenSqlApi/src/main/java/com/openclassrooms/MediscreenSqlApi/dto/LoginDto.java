package com.openclassrooms.MediscreenSqlApi.dto;

import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Praticien;

import java.util.Optional;

public class LoginDto {
    Optional<Patient> patient;

    Optional<Praticien> praticien;

    public LoginDto(Optional<Patient> patient, Optional<Praticien> praticien) {
        this.patient = patient;
        this.praticien = praticien;
    }

    public Optional<Patient> getPatient() {
        return patient;
    }

    public void setPatient(Optional<Patient> patient) {
        this.patient = patient;
    }

    public Optional<Praticien> getPraticien() {
        return praticien;
    }

    public void setPraticien(Optional<Praticien> praticien) {
        this.praticien = praticien;
    }
}
