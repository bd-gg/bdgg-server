package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.GameHistoryDTO;
import gg.boardgame.bdgg.dto.IndividualGameResultDTO;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGameHistoryRepository userGameHistoryRepository;

    @Autowired
    private UserMatchRepository userMatchRepository;

    @Override
    public ProfileDTO getProfile(String userName, Pageable pageable) throws ResourceNotFoundException {

        User user = userRepository.findByName(userName).orElseThrow(() -> new ResourceNotFoundException("user is not found for this name:: " + userName));

        long userId = user.getId();
        Page<UserGameHistory> userGameHistories = userGameHistoryRepository.findByUser_Id(userId, pageable);

        ProfileDTO profile = new ProfileDTO(user);

        userGameHistories.getContent().forEach(u -> {
            GameHistoryDTO item = new GameHistoryDTO();
            item.setGameId(u.getGameId());
            item.setCount(u.getCount());
            profile.getMostPlayed().add(item);
        });

        return profile;
    }
}
