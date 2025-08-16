package com.badminton.scoreboard.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.badminton.scoreboard.model.Score;
import com.badminton.scoreboard.model.Match;
import com.badminton.scoreboard.model.Player;
import com.badminton.scoreboard.exception.MatchNotFoundException;
import com.badminton.scoreboard.exception.PlayerNotFoundException;
import com.badminton.scoreboard.mapper.MatchMapper;
import com.badminton.scoreboard.repository.MatchRepository;
import com.badminton.scoreboard.dto.MatchRequestDTO;
import com.badminton.scoreboard.dto.MatchResponseDTO;
import com.badminton.scoreboard.repository.PlayerRepository;
import com.badminton.scoreboard.repository.ScoreRepository;

import lombok.RequiredArgsConstructor;





@Service

@RequiredArgsConstructor
public class MatchService {


    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final ScoreRepository scoreRepository;

    
   

    public ResponseEntity<MatchResponseDTO> createMatch(MatchRequestDTO dto) {
        Player player1 = playerRepository.findById(dto.getPlayer1Id()).orElseThrow(() -> new PlayerNotFoundException("Player 1 not found"));
        Player player2 = playerRepository.findById(dto.getPlayer2Id()).orElseThrow(() -> new PlayerNotFoundException("Player 2 not found"));
        Match match = new Match();
        match.setMatchType(dto.getMatchType());
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        // Initialize scores only if the list is not empty
        if (match.getPlayer1().getScores() != null && !match.getPlayer1().getScores().isEmpty()) {
            match.getPlayer1().getScores().get(0).setPlayerScore(0);
        }
        if (match.getPlayer2().getScores() != null && !match.getPlayer2().getScores().isEmpty()) {
            match.getPlayer2().getScores().get(0).setPlayerScore(0);
        }
        matchRepository.save(match);
        MatchResponseDTO response = MatchMapper.toDTO(match);
        return ResponseEntity.ok().body(response);
    }

    
    public ResponseEntity<MatchResponseDTO> getScoreboardForMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
        .orElseThrow(() -> new MatchNotFoundException("Match not found"));

    MatchResponseDTO dto = MatchMapper.toDTO(match);

    return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<MatchResponseDTO> setScoreBoardForAMatch(Long matchId, Long playerId) {
        Match match = matchRepository.findByIdAndPlayerId(matchId, playerId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));

        if (match.getPlayer1().getId().equals(playerId)) {
            if (match.getPlayer1().getScores() != null && !match.getPlayer1().getScores().isEmpty()) {
                Score scoreObj = match.getPlayer1().getScores().get(0);
                int updateScore = scoreObj.getPlayerScore() + 1;
                scoreObj.setPlayerScore(updateScore);
                

                System.out.println("Updating score for Player 1: " + updateScore);
                scoreRepository.save(scoreObj);
                System.out.println("Player 1's scores: " + match.getPlayer1().getScores());
            } else {
                // Create new Score if none exists
                Score newScore = new Score();
                newScore.setPlayer(match.getPlayer1());
                newScore.setMatch(match);
                newScore.setPlayerScore(1);
                scoreRepository.save(newScore);
                match.getPlayer1().getScores().add(newScore);
            }
        } else if (match.getPlayer2().getId().equals(playerId)) {
            if (match.getPlayer2().getScores() != null && !match.getPlayer2().getScores().isEmpty()) {
                Score scoreObj = match.getPlayer2().getScores().get(0);
                int updateScore = scoreObj.getPlayerScore() + 1;
                scoreObj.setPlayerScore(updateScore);
                scoreRepository.save(scoreObj);
            } else {
                // Create new Score if none exists
                Score newScore = new Score();
                newScore.setPlayer(match.getPlayer2());
                newScore.setMatch(match);
                newScore.setPlayerScore(1);
                scoreRepository.save(newScore);
                match.getPlayer2().getScores().add(newScore);
            }
        }
        MatchResponseDTO response = MatchMapper.toDTO(match);
        return ResponseEntity.ok().body(response);
    }

    public Optional<MatchResponseDTO> getMatchById(Long id) {
        return matchRepository.findById(id).map(MatchMapper::toDTO);
    }

    // public Boolean decideMatchWinner(Match match) {
    //     int player1Points = getPlayer1Score(match);
    //     int player2Points = getPlayer2Score(match);
    //     // Badminton rules: first to 21, win by 2, max 30
    //     if ((player1Points >= 21 && player1Points - player2Points >= 2) || player1Points == 30) {
    //         match.setWinner(match.getPlayer1());
    //         return true;
    //     } else if ((player2Points >= 21 && player2Points - player1Points >= 2) || player2Points == 30) {
    //         match.setWinner(match.getPlayer2());
    //         return true;
    //     } else {
    //         throw new WinnerNotFoundException("No winner could be determined");
    //     }
    //     matchRepository.save(match);
    // }

    public int getPlayer1Score(Match match) {
        int score = 0;
        Optional<Player> player1 = playerRepository.findById(match.getPlayer1().getId());
        for (Score s : match.getPlayer1().getScores()) {
            if( player1.isPresent() && s.getPlayer() != null && s.getPlayer().getId().equals(player1.get().getId())) {
                score+=s.getPlayerScore();
            }
        }
        return score;
    }

    public int getPlayer2Score(Match match) {
        int score = 0;
        Optional<Player> player2 = playerRepository.findById(match.getPlayer2().getId());
        for (Score s : match.getPlayer2().getScores()) {
            if( player2.isPresent() && s.getPlayer() != null && s.getPlayer().getId().equals(player2.get().getId())) {
                score+=s.getPlayerScore();
            }
        }
        return score;
    }

    public List<MatchResponseDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(MatchMapper::toDTO)
                .toList();
    }

   


}
