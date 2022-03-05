package com.openclassrooms.MediscreenSqlApi.service;

import com.openclassrooms.MediscreenSqlApi.entity.Patient;
import com.openclassrooms.MediscreenSqlApi.entity.Praticien;
import com.openclassrooms.MediscreenSqlApi.repository.PraticienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PraticienService {
    @Autowired
    PraticienRepository praticienRepository;

    @Transactional(readOnly = true)
    public Praticien findOneByEmail(String email) {
        Praticien praticien = null;
        try {
            praticien = praticienRepository.findOneByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return praticien;
    }

    public Optional<Praticien> findOneById(Long id) {
        Optional<Praticien> praticien = null;
        try {
            praticien = praticienRepository.findOneById(id);
        } catch (Exception e) {
            throw e;
        }
        return praticien;
    }

    public List<Praticien> findAll() {
        List<Praticien> praticiens = new ArrayList<Praticien>();
        try {
            praticiens = praticienRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
        return praticiens;
    }
}
