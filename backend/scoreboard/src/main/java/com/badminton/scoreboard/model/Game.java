package com.badminton.scoreboard.model;

import java.time.Instant;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int gameNumber;
    private Instant startedAt;
    private Instant endedAt;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;
}