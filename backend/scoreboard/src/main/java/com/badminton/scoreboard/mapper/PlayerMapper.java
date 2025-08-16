package com.badminton.scoreboard.mapper;

import com.badminton.scoreboard.dto.PlayerResponseDTO;
import com.badminton.scoreboard.model.Player;

public class PlayerMapper {

    public static PlayerResponseDTO toDTO(Player player) {
        PlayerResponseDTO dto = new PlayerResponseDTO();
        dto.setName(player.getName());
        dto.setEmail(player.getEmail());
        dto.setCountry(player.getCountry());
        dto.setAge(player.getAge());
        if (player.getScores() != null && !player.getScores().isEmpty()) {
            int totalScore = player.getScores().stream().mapToInt(score -> score.getPlayerScore()).sum();
            dto.setScore(totalScore);
            // Set scoreDetails to the latest score
            dto.setScoreDetails(
                com.badminton.scoreboard.mapper.ScoreMapper.toDTO(
                    player.getScores().get(player.getScores().size() - 1)
                )
            );
        } else {
            dto.setScore(null);
            dto.setScoreDetails(null);
        }
        return dto;
    }

    public static Player toEntity(PlayerResponseDTO dto) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setEmail(dto.getEmail());
        player.setCountry(dto.getCountry());
        player.setAge(dto.getAge());
        return player;
    }

}
