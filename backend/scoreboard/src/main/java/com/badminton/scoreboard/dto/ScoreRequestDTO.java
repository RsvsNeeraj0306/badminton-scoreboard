package com.badminton.scoreboard.dto;

import lombok.Data;

@Data
public class ScoreRequestDTO {

    private int playerScore;
    private Long matchId;
    private Long playerId;

}
