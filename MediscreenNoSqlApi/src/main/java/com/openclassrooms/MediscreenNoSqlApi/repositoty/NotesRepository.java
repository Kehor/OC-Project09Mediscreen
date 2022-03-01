package com.openclassrooms.MediscreenNoSqlApi.repositoty;

import com.openclassrooms.MediscreenNoSqlApi.entity.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Notes, Long> {

    List<Notes> findBypatientId(Long patientId);
    Notes findByid(Long id);
}
