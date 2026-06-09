package com.prueba.futbol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "training_results")
public class TrainingResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "training_id")
    private Long trainingId;

    @Column(name = "shot_power")
    private Double shotPower;

    @Column(name = "spped")
    private Double spped;

    @Column(name = "passes")
    private Integer passes;

    @Column(name = "score")
    private Double score;
}
