package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    List<Long> getMatchIds(long id, Pageable pageable) throws ResourceNotFoundException;
}
