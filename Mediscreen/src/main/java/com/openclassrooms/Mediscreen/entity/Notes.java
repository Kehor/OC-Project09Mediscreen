package com.openclassrooms.Mediscreen.entity;


import java.time.LocalDateTime;
import java.util.Date;

public class Notes {

    private Long id;

    private Long patientId;

    private Date createdAt;

    private Date modifiedAt;

    private String practitionnerNotesRecommandation;

    public Notes() {
    }

    public Notes(Long id, Long patientId, Date createdAt, Date modifiedAt, String practitionnerNotesRecommandation) {
        this.id = id;
        this.patientId = patientId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.practitionnerNotesRecommandation = practitionnerNotesRecommandation;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getPractitionnerNotesRecommandation() {
        return practitionnerNotesRecommandation;
    }

    public void setPractitionnerNotesRecommandation(String practitionnerNotesRecommandation) {
        this.practitionnerNotesRecommandation = practitionnerNotesRecommandation;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", practitionnerNotesRecommandation='" + practitionnerNotesRecommandation + '\'' +
                '}';
    }
}
