package com.openclassrooms.MediscreenSqlApi.repository;

import com.openclassrooms.MediscreenSqlApi.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAll();
}
