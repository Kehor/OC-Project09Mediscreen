package com.openclassrooms.MediscreenSqlApi.repository;

import com.openclassrooms.MediscreenSqlApi.entity.Appointment;
import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Patient, Long> {
    List<Appointment> findAllByPatientId(Long id);
    List<Appointment> findAllByPraticienId(Long id);
    Appointment findOneByPraticienIdAndPatientId(Long praticienId,Long patientId);
    Appointment save(Appointment appointment);
    void deleteById(Long id);
}
