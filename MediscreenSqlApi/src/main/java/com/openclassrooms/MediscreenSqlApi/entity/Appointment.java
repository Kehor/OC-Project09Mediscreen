package com.openclassrooms.MediscreenSqlApi.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="patient_id")
    private Long patientId;

    @Column(name="praticien_id")
    private Long praticienId;

    @Column(name="reserved_at")
    private Date reservedAt;

    @Column(name="created_at")
    private Date createdAt;

    public Appointment() {
    }

    public Appointment(Long id, Long patientId, Long praticienId, Date reservedAt, Date createdAt) {
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
