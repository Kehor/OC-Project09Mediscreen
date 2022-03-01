package com.openclassrooms.Mediscreen.proxy;

import com.openclassrooms.Mediscreen.entity.Notes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservice-NoSqlApi", url = "http://localhost:8082")
public interface NoSqlApiProxy {


    @PostMapping(value = "/notes/getbypatientid")
    List<Notes> getNotesbyPatientid(@RequestParam Long id);


    @PostMapping(value = "/notes/save")
    Notes saveNotes(@RequestParam Long id, @RequestParam Long idpatient, @RequestParam String practitionnerNotesRecommandation);

}
