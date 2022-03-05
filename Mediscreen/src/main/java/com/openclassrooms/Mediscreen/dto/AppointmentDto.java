package com.openclassrooms.Mediscreen.dto;


import java.util.Date;


public class AppointmentDto {

    private Long id;

    private Long patientId;

    private Long praticienId;

    private Date reservedAt;

    private Date createdAt;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, Long patientId, Long praticienId, Date reservedAt, Date createdAt) {
        this.id = id;
        this.patientId = patientId;
        this.praticienId = praticienId;
        this.reservedAt = reservedAt;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPraticienId() {
        return praticienId;
    }

    public void setPraticienId(Long praticienId) {
        this.praticienId = praticienId;
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
