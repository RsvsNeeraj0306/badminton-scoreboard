package com.badminton.scoreboard.dto;

import java.time.Instant;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class ScoreResponseDTO {

    private Long id;
    private int playerScore;
    private Long playerId;
    private Instant timestamp;

}
