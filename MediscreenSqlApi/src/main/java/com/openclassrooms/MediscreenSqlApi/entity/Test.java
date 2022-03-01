package com.openclassrooms.MediscreenSqlApi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="praticient")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="risk")
    private String risk;

    public Test(Long id) {
        this.id = id;
    }

    public Test(Long id, String risk) {
        this.id = id;
        this.risk = risk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
