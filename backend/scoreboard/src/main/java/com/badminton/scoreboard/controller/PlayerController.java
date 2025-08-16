package com.badminton.scoreboard.controller;

import com.badminton.scoreboard.dto.PlayerRequestDTO;
import com.badminton.scoreboard.dto.PlayerResponseDTO;
import com.badminton.scoreboard.service.PlayerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody PlayerRequestDTO playerRequestDTO) {
        PlayerResponseDTO createdPlayer = playerService.createPlayer(playerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayer(@PathVariable Long id) {
        PlayerResponseDTO player = playerService.getPlayerById(id);
        return ResponseEntity.ok().body(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDTO playerRequestDTO) {
        PlayerResponseDTO updatedPlayer = playerService.updatePlayer(id, playerRequestDTO);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        List<PlayerResponseDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }   

}
