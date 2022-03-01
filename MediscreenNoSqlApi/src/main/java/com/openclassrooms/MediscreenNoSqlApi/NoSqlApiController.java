package com.openclassrooms.MediscreenNoSqlApi;

import com.openclassrooms.MediscreenNoSqlApi.entity.Notes;
import com.openclassrooms.MediscreenNoSqlApi.repositoty.NotesRepository;
import com.openclassrooms.MediscreenNoSqlApi.service.NotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoSqlApiController {

    @Autowired
    private NotesService notesService;

    private Logger logger = LoggerFactory.getLogger(NoSqlApiController.class);

    @GetMapping("/")
    public ResponseEntity SqlApiHome() {
        logger.info("NoSqlApiController : NoSqlApiHome");
        return ResponseEntity.ok("NoSqlApi");
    }

    @PostMapping("/notes/getbypatientid")
    public ResponseEntity<List<Notes>> getbypatient(@RequestParam(name="id", required=true) Long id) {
        logger.info("NoSqlApiController : getbypatient");
        List<Notes> notes = notesService.findBypatientId(id);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/notes/save")
    public ResponseEntity<Notes> save(@RequestParam(name="id", required=true) Long id, @RequestParam(name="idpatient", required=true) Long idpatient, @RequestParam(name="practitionnerNotesRecommandation", required=true) String practitionnerNotesRecommandation) {
        logger.info("NoSqlApiController : saveNotes");
        Notes notes = notesService.save(id,idpatient,practitionnerNotesRecommandation);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/notes/triggers")
    public ResponseEntity<Integer> getTriggersIteration(@RequestParam(name="idpatient", required=true) Long id) {
        logger.info("NoSqlApiController : getTriggersIteration");
        int triggers = notesService.getTriggersIteration(id);
        return ResponseEntity.ok(triggers);
    }
}
