package com.badminton.scoreboard.controller;

import com.badminton.scoreboard.dto.MatchRequestDTO;
import com.badminton.scoreboard.dto.MatchResponseDTO;
import com.badminton.scoreboard.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        return matchService.createMatch(matchRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatch(@PathVariable Long id) {
        Optional<MatchResponseDTO> match = matchService.getMatchById(id);
        return match.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MatchResponseDTO>> getAllMatches() {
        List<MatchResponseDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> updateScoreboard(@PathVariable Long id, @RequestParam Long playerId) {
        return matchService.setScoreBoardForAMatch(id, playerId);
    }

    @GetMapping("/{id}/scoreboard")
    public ResponseEntity<MatchResponseDTO> getScoreboard(@PathVariable Long id) {
        return matchService.getScoreboardForMatch(id);
    }

}
