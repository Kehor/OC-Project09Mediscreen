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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    public Appointment save(Long id, Long patientid, Long praticienid, String reservedAt){
        Optional<Patient> optionalpatient = patientService.findOneById(patientid);
        Optional<Praticien> optionalpraticien = praticienService.findOneById(praticienid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd'T'HH:mm", Locale.FRANCE);
        Date reservedAtdate = null;
        try {
            reservedAtdate = formatter.parse(reservedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(optionalpatient.isPresent() && optionalpraticien.isPresent()) {
            Long patient = optionalpatient.get().getId();
            Long praticien = optionalpraticien.get().getId();
            Appointment appointment = new Appointment(id,Optional.of(patient).get(),Optional.of(praticien).get(),reservedAtdate,new Date());
            appointmentRepository.save(appointment);
            return appointment;
        }else {
            logger.error("patient and/or praticien not found");
            return new Appointment();
        }
    }

    public Boolean deleteByid(Long id, Long patientid, Long praticienid){
        Optional<List<Appointment>> optionalAppointment = Optional.ofNullable(appointmentRepository.findAllByPraticienIdAndPatientId(patientid, praticienid));
        if(optionalAppointment.isPresent()) {
        appointmentRepository.deleteById(id);
        }else {
            logger.error("Appointment not found");
            return false;
        }
        return true;
    }
}
