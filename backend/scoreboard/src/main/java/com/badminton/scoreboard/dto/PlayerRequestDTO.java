package com.badminton.scoreboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;
    private String country;
    private int age;
}
