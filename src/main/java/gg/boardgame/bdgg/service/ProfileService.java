package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    ProfileDTO getProfile(Integer userId, Pageable pageable);
    List<MatchDTO> getMatchList(Integer userId, Integer gameId, Integer gameType, Pageable pageable);
}
