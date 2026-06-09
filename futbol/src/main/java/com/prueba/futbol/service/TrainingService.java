package com.prueba.futbol.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.futbol.dto.HttpGlobalResponse;
import com.prueba.futbol.dto.MessageResponseDTO;
import com.prueba.futbol.dto.PlayerTrainingDTO;
import com.prueba.futbol.dto.StartingTeamResponseDTO;
import com.prueba.futbol.dto.TrainingRequestDTO;
import com.prueba.futbol.entity.Player;
import com.prueba.futbol.entity.Training;
import com.prueba.futbol.entity.TrainingResult;
import com.prueba.futbol.repository.PlayerRepository;
import com.prueba.futbol.repository.TrainingRepository;
import com.prueba.futbol.repository.TrainingResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingResultRepository trainingResultRepository;

    private final PlayerRepository playerRepository;

    public MessageResponseDTO saveTraining(TrainingRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();

        for (PlayerTrainingDTO player : request.getPlayers()) {

            Optional<Player> playerFound = playerRepository.findById(player.getPlayerId());

            if (playerFound.isEmpty()) {

                response.setMessage("Jugador con id " + player.getPlayerId() + " no se encuentra registrado");
                return response;
            }

        }

        Training training = new Training();

        training.setTrainingNumber(request.getTrainingNumber());
        training.setTrainingDate(LocalDate.now());

        training = trainingRepository.save(training);

        for (PlayerTrainingDTO player : request.getPlayers()) {

            double score = (player.getShotPower() * 0.20) + (player.getSpeed() * 0.30) + (player.getPasses() * 0.50);

            TrainingResult result = new TrainingResult();

            result.setPlayerId(player.getPlayerId());
            result.setTrainingId(player.getTrainingId());
            result.setShotPower(player.getShotPower());
            result.setSpped(player.getSpeed());
            result.setPasses(player.getPasses());
            result.setScore(score);

            trainingResultRepository.save(result);
        }

        response.setMessage("Entrenamiento guardado correctamente");

        return response;
    }

    public HttpGlobalResponse<List<StartingTeamResponseDTO>> getStartingTeam() {
        HttpGlobalResponse<List<StartingTeamResponseDTO>> response = new HttpGlobalResponse<>();

        List<Training> trainings = trainingRepository.findAll();

        if (trainings.size() < 3) {
            response.setMessage("No hay suficiente información");
            response.setData(new ArrayList<>());

            return response;

        }

        List<Player> players = playerRepository.findAll();

        List<TrainingResult> results = trainingResultRepository.findAll();

        List<StartingTeamResponseDTO> startingTeam = new ArrayList<>();

        for (Player player : players) {

            double totalScore = 0;
            int trainingsCount = 0;

            for (TrainingResult result : results) {

                if (result.getPlayerId().equals(player.getId())) {

                    totalScore += result.getScore();
                    trainingsCount++;

                }
            }

            if (trainingsCount > 0) {
                double averageScore = totalScore / trainingsCount;

                StartingTeamResponseDTO dto = new StartingTeamResponseDTO();

                dto.setPlayerId(player.getId());
                dto.setPlayerName(player.getName());
                dto.setAverageScore(averageScore);

                startingTeam.add(dto);
            }
        }

        for (int i = 0; i < startingTeam.size() - 1; i++) {

            for (int j = 0; j < startingTeam.size(); j++) {

                if (startingTeam.get(i).getAverageScore() < startingTeam.get(j).getAverageScore()) {

                    StartingTeamResponseDTO temp = startingTeam.get(i);

                    startingTeam.set(i, startingTeam.get(j));

                    startingTeam.set(j, temp);
                }
            }
        }

        List<StartingTeamResponseDTO> topPlayers = new ArrayList<>();

        if (startingTeam.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                topPlayers.add(startingTeam.get(i));
            }
        } else {

            topPlayers = startingTeam;
        }

        response.setMessage("Equipo titular obtenido correctamente");

        response.setData(topPlayers);

        return response;
    }

}
