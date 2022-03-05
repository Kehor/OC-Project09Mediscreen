package com.openclassrooms.Mediscreen.entity;

public class Test {

    private Long id;

    private String risk;

    public Test() {
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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", risk='" + risk + '\'' +
                '}';
    }
}
