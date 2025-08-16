// package com.badminton.scoreboard.service;


// import com.badminton.scoreboard.dto.GameRequestDTO;
// import com.badminton.scoreboard.dto.GameResponseDTO;
// import com.badminton.scoreboard.model.Game;
// import com.badminton.scoreboard.repository.ScoreRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.Instant;
// import java.util.Optional;

// @Service
// @RequiredArgsConstructor
// public class GameService {
//     @Autowired
//     private final ScoreRepository gameRepository;

//     public GameResponseDTO createGame() {
//         Game game = new Game();
//         game.setScoreP1(0);
//         game.setScoreP2(0);
//         game.setStartedAt(Instant.now());
//         Game saved = gameRepository.save(game);
//         return toDTO(saved);
//     }

//     public Optional<GameDTO> findById(Long id) {
//         return gameRepository.findById(id).map(this::toDTO);
//     }

//     private GameResponseDTO toDTO(Game game) {
//         GameResponseDTO dto = new GameResponseDTO();
//         dto.setId(game.getId());
//         dto.setGameNumber(game.getGameNumber());
//         dto.setScoreP1(game.getScoreP1());
//         dto.setScoreP2(game.getScoreP2());
//         dto.setWinnerPlayerId(game.getWinnerPlayerId());
//         dto.setStartedAt(game.getStartedAt());
//         dto.setEndedAt(game.getEndedAt());
//         return dto;
//     }

//     public Optional<GameDTO> updateScore(Long gameId, Integer scoreP1, Integer scoreP2) {
//         Optional<Game> optionalGame = gameRepository.findById(gameId);
//         if (optionalGame.isPresent()) {
//             Game game = optionalGame.get();
//             game.setScoreP1(scoreP1);
//             game.setScoreP2(scoreP2);
//             Game updated = gameRepository.save(game);
//             return Optional.of(toDTO(updated));
//         }
//         return Optional.empty();
//     }
// }
