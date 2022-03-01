package com.openclassrooms.Mediscreen.microservice;

import com.openclassrooms.Mediscreen.entity.Notes;

import java.util.List;

public interface NoSqlApiMicroservice {


    public List<Notes> getNotesbyPatientid(Long id);

    public Notes saveNotes(Long id, Long idpatient, String practitionnerNotesRecommandation);
}
