package com.openclassrooms.Mediscreen.microservice;


import com.openclassrooms.Mediscreen.entity.Notes;
import com.openclassrooms.Mediscreen.proxy.NoSqlApiProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class NoSqlApiMicroserviceImpl implements NoSqlApiMicroservice {
    @Autowired
    private NoSqlApiProxy noSqlApiProxy;

    @Override
    public List<Notes> getNotesbyPatientid(Long id){
        List<Notes> notes = noSqlApiProxy.getNotesbyPatientid(id);
        return notes;
    }

    @Override
    public Notes saveNotes(Long id, Long idpatient, String practitionnerNotesRecommandation){
        Notes notes = noSqlApiProxy.saveNotes(id,idpatient,practitionnerNotesRecommandation);
        return notes;
    }
}
