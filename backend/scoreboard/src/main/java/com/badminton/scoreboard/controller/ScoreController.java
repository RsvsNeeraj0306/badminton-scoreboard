package com.badminton.scoreboard.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badminton.scoreboard.dto.ScoreRequestDTO;
import com.badminton.scoreboard.dto.ScoreResponseDTO;
import com.badminton.scoreboard.service.ScoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

	@PostMapping    
	public ResponseEntity<ScoreResponseDTO> createScore(@RequestBody ScoreRequestDTO scoreRequest) {
		ScoreResponseDTO createdScore = scoreService.createScore(scoreRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdScore);
	}

    @GetMapping
    public ResponseEntity<List<ScoreResponseDTO>> getAllScores() {
        return scoreService.getAllScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreResponseDTO> getScoreById(@PathVariable Long id) {
        return scoreService.getScoreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
