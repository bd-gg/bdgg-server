package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.MatchDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {
    public List<MatchDTO> getMatchList(Integer userId, Integer gameId, Integer gameType, Pageable pageable);
}
