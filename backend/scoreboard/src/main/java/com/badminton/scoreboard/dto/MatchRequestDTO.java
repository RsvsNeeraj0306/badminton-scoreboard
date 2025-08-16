package com.badminton.scoreboard.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MatchRequestDTO {
   
    private String matchType;

    @NotBlank(message = "Player 1 ID is mandatory")
    private Long player1Id;

    @NotBlank(message = "Player 2 ID is mandatory")
    private Long player2Id;
}
