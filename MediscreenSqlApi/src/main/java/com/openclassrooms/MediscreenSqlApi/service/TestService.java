package com.openclassrooms.MediscreenSqlApi.service;

import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Test;
import com.openclassrooms.MediscreenSqlApi.microservice.NoSqlApiMicroservice;
import com.openclassrooms.MediscreenSqlApi.repository.PatientRepository;
import com.openclassrooms.MediscreenSqlApi.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private NoSqlApiMicroservice noSqlApiMicroservice;
    @Autowired
    private PatientService patientService;

    public Test gettest(Long id){
        List<Test> testList = testRepository.findAll();
        Integer Trigger = noSqlApiMicroservice.getTriggersIteration(id);
        Optional<Patient> patient = patientService.findOneById(id);
        long diffInMillies = new Date().getTime() - patient.get().getDob().getTime();
        long diffInYears = (diffInMillies/31536000000l);
        String sex = patient.get().getSex();
        if(Trigger == 0){
            return testList.get(0);
        }else if (Trigger == 2 && diffInYears >= 30l){
            return testList.get(1);
        }else if(Trigger == 3 && diffInYears <= 30l && sex.contains("M") || Trigger == 4 && diffInYears <= 30l && sex.contains("F") || Trigger == 6 && diffInYears > 30l){
            return testList.get(2);
        }else if (Trigger == 5 && diffInYears <= 30l && sex.contains("M") || Trigger == 7 && diffInYears <= 30l && sex.contains("F") || Trigger >= 8 && diffInYears > 30l){
            return testList.get(3);
        }
        return testList.get(0);
    }
}
