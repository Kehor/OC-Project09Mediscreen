package com.openclassrooms.MediscreenSqlApi.repository;

import com.openclassrooms.MediscreenSqlApi.entity.Praticien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PraticienRepository extends JpaRepository<Praticien, Long> {

    public List<Praticien> findAll();

    public Praticien findOneByEmail(String email);

    public Praticien findOneByPrenomAndNom(String prenom, String nom);

    Optional<Praticien> findOneById(Long id);
}
