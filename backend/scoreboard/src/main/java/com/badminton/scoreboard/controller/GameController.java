// package com.badminton.scoreboard.controller;

// import com.badminton.scoreboard.dto.GameDTO;
// import com.badminton.scoreboard.service.GameService;
// import com.badminton.scoreboard.dto.GameRequestDTO;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @RestController
// @RequestMapping("/api/games")
// @RequiredArgsConstructor
// public class GameController {
//     private final GameService gameService;

//     @PostMapping
//     public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
//         return ResponseEntity.ok(gameService.createGame(gameDTO));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<GameDTO> getGame(@PathVariable Long id) {
//         Optional<GameDTO> game = gameService.findById(id);
//         return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PutMapping("/{id}/score")
//     public ResponseEntity<GameDTO> updateScore(@PathVariable Long id, @RequestBody GameRequestDTO request) {
//         Optional<GameDTO> updated = gameService.updateScore(id, request.getScoreP1(), request.getScoreP2());
//         return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }
// }
