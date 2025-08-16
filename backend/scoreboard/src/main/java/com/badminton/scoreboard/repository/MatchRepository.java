package com.badminton.scoreboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.badminton.scoreboard.model.Match;

public interface MatchRepository extends JpaRepository <Match, Long>{

    Optional<Match> findByIdAndPlayer1_Id(Long matchId, Long player1Id);
    Optional<Match> findByIdAndPlayer2_Id(Long matchId, Long player2Id);

    @Query("SELECT m FROM Match m WHERE m.id = :matchId AND (m.player1.id = :playerId OR m.player2.id = :playerId)")
    Optional<Match> findByIdAndPlayerId(Long matchId, Long playerId);

}
