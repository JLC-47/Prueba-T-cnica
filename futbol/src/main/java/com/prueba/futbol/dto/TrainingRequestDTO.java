package com.prueba.futbol.dto;

import java.util.List;

import lombok.Data;

@Data
public class TrainingRequestDTO {
    
    private Integer trainingNumber;

    private List<PlayerTrainingDTO> players;
}
