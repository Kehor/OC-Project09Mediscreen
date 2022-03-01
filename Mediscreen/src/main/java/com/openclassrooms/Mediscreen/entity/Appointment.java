package com.openclassrooms.Mediscreen.entity;

import java.util.Date;

public class Appointment {
    private Long id;

    private Patient patient;

    private Praticien praticien;

    private Date reservedAt;

    private Date createdAt;

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, Praticien praticien, Date reservedAt, Date createdAt) {
        this.id = id;
        this.patient = patient;
        this.praticien = praticien;
        this.reservedAt = reservedAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
    }

    public Date getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(Date reservedAt) {
        this.reservedAt = reservedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
