package com.prueba.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.futbol.entity.TrainingResult;

@Repository
public interface TrainingResultRepository extends JpaRepository<TrainingResult, Long> {
    
}
