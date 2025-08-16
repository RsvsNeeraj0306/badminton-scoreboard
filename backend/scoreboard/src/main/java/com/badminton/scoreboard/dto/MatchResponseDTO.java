package com.badminton.scoreboard.dto;

import java.time.Instant;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MatchResponseDTO {
    private Long id;
    private Instant startedAt;
    private String matchType; // 'Singles' / 'Doubles'
    private PlayerResponseDTO player1;
    private PlayerResponseDTO player2;
}
