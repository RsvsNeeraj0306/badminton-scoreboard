package com.badminton.scoreboard.mapper;

import com.badminton.scoreboard.dto.MatchResponseDTO;
import com.badminton.scoreboard.model.Match;


public class MatchMapper {

    public static MatchResponseDTO toDTO(Match match) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setId(match.getId());
        dto.setMatchType(match.getMatchType());
    dto.setPlayer1(PlayerMapper.toDTO(match.getPlayer1()));
    dto.setPlayer2(PlayerMapper.toDTO(match.getPlayer2()));
        dto.setStartedAt(match.getStartedAt());
        return dto;
    }
}
