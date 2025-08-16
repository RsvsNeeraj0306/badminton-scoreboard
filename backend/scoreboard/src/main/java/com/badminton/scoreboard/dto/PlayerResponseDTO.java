package com.badminton.scoreboard.dto;

import lombok.Data;

@Data
public class PlayerResponseDTO {
    private String name;
    private String email;
    private String country;
    private Integer score;
    private ScoreResponseDTO scoreDetails; // Uncomment if you want to include detailed score information
    private int age;

}
