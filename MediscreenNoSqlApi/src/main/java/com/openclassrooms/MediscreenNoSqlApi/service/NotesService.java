package com.openclassrooms.MediscreenNoSqlApi.service;

import com.openclassrooms.MediscreenNoSqlApi.NoSqlApiController;
import com.openclassrooms.MediscreenNoSqlApi.entity.Notes;
import com.openclassrooms.MediscreenNoSqlApi.repositoty.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    private Logger logger = LoggerFactory.getLogger(NoSqlApiController.class);

    public Notes save(Long id, Long patientId, String practitionnerNotesRecommandation){
        Date now =  new Date();
        Date createdAt =  new Date();
        if(id == (long)0){
            id = sequenceGeneratorService.generateSequence(Notes.SEQUENCE_NAME);
        }else {
            createdAt = notesRepository.findByid(id).getCreatedAt();
        }
        Notes notes = new Notes(id,patientId,createdAt,now,practitionnerNotesRecommandation);
        notes = notesRepository.save(notes);
        return notes;
    }

    public List<Notes> findBypatientId(Long id){
        List<Notes> notesList = notesRepository.findBypatientId(id);
        return notesList;
    }

    public int getTriggersIteration(Long id) {
        List<Notes> notes = findBypatientId(id);
        Scanner s = null;
        try {
            s = new Scanner(new File("src\\main\\resources\\Triggers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> filter = new ArrayList<String>();
        while (s.hasNextLine()){
            filter.add(s.nextLine().toLowerCase());
        }
        s.close();
        ArrayList<String> recommandations = new ArrayList<>();
        for (Notes note : notes) {
            recommandations.add(note.getPractitionnerNotesRecommandation().toLowerCase());
        }
        String notesString = String.join(" ",recommandations);
        List<String> allRecommandations = new ArrayList<String>(Arrays.asList(notesString.split(" ")));
        allRecommandations.retainAll(filter);
        return allRecommandations.size();
    }
}
