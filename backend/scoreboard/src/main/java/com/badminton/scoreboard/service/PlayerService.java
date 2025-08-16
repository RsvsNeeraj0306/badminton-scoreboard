package com.badminton.scoreboard.service;

import com.badminton.scoreboard.dto.PlayerRequestDTO;
import com.badminton.scoreboard.dto.PlayerResponseDTO;
import com.badminton.scoreboard.exception.EmailAlreadyExistsException;
import com.badminton.scoreboard.exception.PlayerNotFoundException;
import com.badminton.scoreboard.mapper.PlayerMapper;
import com.badminton.scoreboard.model.Player;
import com.badminton.scoreboard.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerResponseDTO createPlayer(PlayerRequestDTO playerRequestDTO) {
        Optional<Player> existingPlayer = playerRepository.findByEmail(playerRequestDTO.getEmail());
        if (existingPlayer.isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        Player player = new Player();
        player.setName(playerRequestDTO.getName());
        player.setEmail(playerRequestDTO.getEmail());
        player.setCountry(playerRequestDTO.getCountry());
        player.setAge(playerRequestDTO.getAge());

        Player savedPlayer = playerRepository.save(player);
        return PlayerMapper.toDTO(savedPlayer);
    }

    public PlayerResponseDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        return PlayerMapper.toDTO(player);
    }

    public PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO playerRequestDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        player.setName(playerRequestDTO.getName());
        player.setEmail(playerRequestDTO.getEmail());
        player.setCountry(playerRequestDTO.getCountry());
        player.setAge(playerRequestDTO.getAge());

        Player updatedPlayer = playerRepository.save(player);
        return PlayerMapper.toDTO(updatedPlayer);
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        playerRepository.delete(player);
    }

    public List<PlayerResponseDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(PlayerMapper::toDTO)
                .toList();
    }

    
}
