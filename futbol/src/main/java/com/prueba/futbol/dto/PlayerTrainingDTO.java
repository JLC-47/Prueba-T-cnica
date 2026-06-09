package com.prueba.futbol.dto;

import lombok.Data;

@Data
public class PlayerTrainingDTO {
    
    private Long playerId;

    private Long trainingId;

    private Double shotPower;

    private Double speed;

    private Integer passes;

}
