package com.prueba.futbol.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.prueba.futbol.dto.HttpGlobalResponse;
import com.prueba.futbol.dto.MessageResponseDTO;
import com.prueba.futbol.dto.StartingTeamResponseDTO;
import com.prueba.futbol.dto.TrainingRequestDTO;
import com.prueba.futbol.service.TrainingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/save-training")
    public ResponseEntity<MessageResponseDTO> saveTraining(@RequestBody TrainingRequestDTO request){
        try {
            MessageResponseDTO response= trainingService.saveTraining(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            
        } catch (Exception e) {
            
            MessageResponseDTO message = new MessageResponseDTO();
            message.setMessage("A ocurrido un error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
        }
    }

    @GetMapping("/starting-team")
    public HttpGlobalResponse<List<StartingTeamResponseDTO>> getStartingTeam() {

        try {
            HttpGlobalResponse<List<StartingTeamResponseDTO>> response = trainingService.getStartingTeam();
            return response;
            
        } catch (Exception e) {
            HttpGlobalResponse<List<StartingTeamResponseDTO>> message = new HttpGlobalResponse<>();
            message.setData(null);
            message.setMessage("Ha ocurrido un error");

            return message;
        }
    }

}
