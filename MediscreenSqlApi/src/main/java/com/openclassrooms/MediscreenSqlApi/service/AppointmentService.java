package com.openclassrooms.MediscreenSqlApi.service;


import com.openclassrooms.MediscreenSqlApi.SqlApiController;
import com.openclassrooms.MediscreenSqlApi.entity.Appointment;
import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Praticien;
import com.openclassrooms.MediscreenSqlApi.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PraticienService praticienService;

    private Logger logger = LoggerFactory.getLogger(SqlApiController.class);

    public List<Appointment> findAllByPatient(Long id){
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(id);
        return appointments;
    }

    public List<Appointment> findAllByPraticien(Long id){
        List<Appointment> appointments = appointmentRepository.findAllByPraticienId(id);
        return appointments;
    }

    public Appointment save(Long id, Long patientid, Long praticienid, Date reservedAt){
        Optional<Patient> optionalpatient = patientService.findOneById(patientid);
        Optional<Praticien> optionalpraticien = praticienService.findOneById(praticienid);
        if(optionalpatient.isPresent() && optionalpraticien.isPresent()) {
            Long patient = optionalpatient.get().getId();
            Long praticien = optionalpraticien.get().getId();
            Appointment appointment = new Appointment(id,Optional.of(patient).get(),Optional.of(praticien).get(),reservedAt,new Date());
            appointmentRepository.save(appointment);
            return appointment;
        }else {
            logger.error("patient and/or praticien not found");
            return new Appointment();
        }
    }

    public Boolean deleteByid(Long id, Long patientid, Long praticienid){
        Optional<Appointment> optionalAppointment = Optional.ofNullable(appointmentRepository.findOneByPraticienIdAndPatientId(patientid, praticienid));
        if(optionalAppointment.isPresent()) {
        appointmentRepository.deleteById(id);
        }else {
            logger.error("Appointment not found");
            return false;
        }
        return true;
    }
}
