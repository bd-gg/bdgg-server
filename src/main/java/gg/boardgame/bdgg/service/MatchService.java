package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface MatchService {
    MatchDTO.Response getMatch(long matchId, Pageable pageable) throws ResourceNotFoundException;
}
