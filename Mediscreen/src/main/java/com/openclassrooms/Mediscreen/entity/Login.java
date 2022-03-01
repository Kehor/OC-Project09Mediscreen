package com.openclassrooms.Mediscreen.entity;


import java.util.Optional;

public class Login {
    Optional<Patient> patient;

    Optional<Praticien> praticien;

    public Login(Optional<Patient> patient, Optional<Praticien> praticien) {
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
