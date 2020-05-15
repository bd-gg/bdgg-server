package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.Match;
import gg.boardgame.bdgg.db.UserMatch;
import gg.boardgame.bdgg.db.UserMatchRepository;
import gg.boardgame.bdgg.dto.IndividualGameResultDTO;
import gg.boardgame.bdgg.dto.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private UserMatchRepository userMatchRepository;

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
//            item.setPlayedTime(match.getPlayTime());
            // need to set time
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
