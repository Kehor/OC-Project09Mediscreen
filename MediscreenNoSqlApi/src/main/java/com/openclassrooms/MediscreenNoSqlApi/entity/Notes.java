package com.openclassrooms.MediscreenNoSqlApi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="recommendations")
public class Notes {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    @Field(value = "patient_id")
    private Long patientId;

    @Field(value = "created_at")
    private Date createdAt;

    @Field(value = "modified_at")
    private Date modifiedAt;

    @Field(value = "practitionner_notes_recommandation")
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
}
