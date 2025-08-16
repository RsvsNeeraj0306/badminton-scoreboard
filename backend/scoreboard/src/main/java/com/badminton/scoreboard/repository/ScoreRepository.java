package com.badminton.scoreboard.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.badminton.scoreboard.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByMatch_IdAndPlayer_Id(Long matchId, Long playerId);
}
