package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    ProfileDTO getProfile(String userName, Pageable pageable) throws ResourceNotFoundException;
}
