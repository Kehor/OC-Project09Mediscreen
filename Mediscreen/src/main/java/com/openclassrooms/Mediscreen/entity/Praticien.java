package com.openclassrooms.Mediscreen.entity;

import java.util.Date;

public class Praticien {
    private Long id;

    private String email;

    private String password;

    private String prenom;

    private String nom;

    private String sex;

    private String address;

    private String phone;

    private Date createdAt;
/*
    @OneToMany( targetEntity=Appointment.class, mappedBy="praticient" )
    private List<Appointment> appointments = new ArrayList<>();
*/
    public Praticien() {
    }

    public Praticien(Long id, String email, String password, String prenom, String nom, String sex, String address, String phone, Date createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
