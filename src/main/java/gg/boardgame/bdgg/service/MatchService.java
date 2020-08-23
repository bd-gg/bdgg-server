package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchService {
    MatchDTO getMatch(long matchId) throws ResourceNotFoundException;
}
