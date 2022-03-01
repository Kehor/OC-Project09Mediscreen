package com.openclassrooms.MediscreenSqlApi.repository;

import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Patient findOneByEmail(String email);

    public Patient findOneByPrenomAndNom(String prenom, String nom);

    Optional<Patient> findOneById(Long id);

    public Patient save(Patient patient);
}
