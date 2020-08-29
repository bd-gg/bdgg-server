package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserMatchRepository userMatchRepository;

    @Override
    public MatchDTO getMatch(long matchId, Pageable pageable) throws ResourceNotFoundException {

        Match match = matchRepository.findById(matchId).orElseThrow(() -> new ResourceNotFoundException("match is not found for this match id:: " + matchId));
        MatchDTO matchDTO = new MatchDTO(match);

        Page<UserMatch> userMatches = userMatchRepository.findByMatch_Id(matchId, pageable).orElseThrow(() -> new ResourceNotFoundException("user-match is not found for this match id:: " + matchId));;

        List<Long> userIds = new ArrayList<>();

        userMatches.getContent().forEach(userMatch -> {
            userIds.add(userMatch.getUser().getId());
        });

        return matchDTO;
    }
}
