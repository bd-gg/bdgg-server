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

    @Override
    public List<MatchDTO> getMatchList(Integer userId, Integer gameId, Integer gameType, Pageable pageable) {
        List<MatchDTO> matches = new ArrayList<>();

        Page<UserMatch> foundMatches = null;

        if(gameId == null && gameType == null)
            foundMatches = userMatchRepository.findByUser_Id(userId, pageable);
        else if(gameId == null && gameType != null)
            foundMatches = userMatchRepository.findByUser_IdAndMatch_GameType(userId, gameType, pageable);
        else if(gameId != null && gameType == null)
            foundMatches = userMatchRepository.findByUser_IdAndMatch_GameNo(userId, gameId, pageable);
        else
            foundMatches = userMatchRepository.
                    findByUser_IdAndMatch_GameNoAndMatch_GameType(userId, gameId, gameType, pageable);

        foundMatches.getContent().forEach(userMatch -> {
            MatchDTO item = new MatchDTO();
            Match match = userMatch.getMatch();
            item.setId(match.getId());
            item.setGameNo(match.getGameNo());
            item.setGameType(match.getGameType());
            item.setPlayedTime(match.getPlayedTime());
            item.setGroupId(match.getGroup().getId());
            item.setPlace("nee to set place");
            item.setResult(userMatch.getScore());

            //set playWith
            List<IndividualGameResultDTO> playWithList = new ArrayList<>();
            userMatchRepository.findByMatch_Id(match.getId(), null)
                    .getContent().forEach(friendMatch -> {
                        IndividualGameResultDTO igr = new IndividualGameResultDTO();
                        igr.setUserId(friendMatch.getUser().getId());
                        igr.setName(friendMatch.getUser().getName());
                        igr.setResult(friendMatch.getScore());
                        playWithList.add(igr);
                    });

            item.setPlayWith(playWithList);
            matches.add(item);
        });

        return matches;
    }
}
