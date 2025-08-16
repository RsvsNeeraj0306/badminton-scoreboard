package com.badminton.scoreboard.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.badminton.scoreboard.dto.ScoreRequestDTO;
import com.badminton.scoreboard.dto.ScoreResponseDTO;

import com.badminton.scoreboard.mapper.ScoreMapper;
import com.badminton.scoreboard.model.Score;
import com.badminton.scoreboard.repository.MatchRepository;
import com.badminton.scoreboard.repository.PlayerRepository;
import com.badminton.scoreboard.repository.ScoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;


    public ScoreResponseDTO createScore(ScoreRequestDTO scoreDTO) {
        Optional<Score> existingScore = scoreRepository.findByMatch_IdAndPlayer_Id(scoreDTO.getMatchId(), scoreDTO.getPlayerId());
        if (existingScore.isPresent()) {
            throw new IllegalArgumentException("Score for this match and player already exists");
        }

        Score score = new Score();
        score.setPlayerScore(scoreDTO.getPlayerScore());
        score.setMatch(matchRepository.findById(scoreDTO.getMatchId()).orElseThrow(() -> new IllegalArgumentException("Match not found")));
        score.setPlayer(playerRepository.findById(scoreDTO.getPlayerId()).orElseThrow(() -> new IllegalArgumentException("Player not found")));
        Score savedScore = scoreRepository.save(score);
        return ScoreMapper.toDTO(savedScore);
    }

    public ResponseEntity<List<ScoreResponseDTO>> getAllScores() {
        List<ScoreResponseDTO> scores = scoreRepository.findAll()
                .stream()
                .map(ScoreMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scores);
    }

    public Optional<ScoreResponseDTO> getScoreById(Long id) {
        return scoreRepository.findById(id)
                .map(ScoreMapper::toDTO);
    }


}
