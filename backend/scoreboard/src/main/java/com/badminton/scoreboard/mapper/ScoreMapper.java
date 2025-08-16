package com.badminton.scoreboard.mapper;

import com.badminton.scoreboard.dto.ScoreResponseDTO;
import com.badminton.scoreboard.model.Score;

public class ScoreMapper {

    public static ScoreResponseDTO toDTO(Score score) {
        ScoreResponseDTO dto = new ScoreResponseDTO();
        dto.setPlayerScore(score.getPlayerScore());
        dto.setPlayerId(score.getPlayer() != null ? score.getPlayer().getId() : null);
        dto.setTimestamp(score.getTimestamp());
        dto.setId(score.getId());
        return dto;
    }
}
