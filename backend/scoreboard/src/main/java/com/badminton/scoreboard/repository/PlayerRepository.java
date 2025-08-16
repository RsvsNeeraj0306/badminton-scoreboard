package com.badminton.scoreboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.badminton.scoreboard.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Custom query methods can be added here if needed
    // For example, to find a player by email:
    Optional<Player> findByEmail(String email);
    
    Optional<Player> findByNameAndEmail(String name, String email);

}
