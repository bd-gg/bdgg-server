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
    public MatchDTO.Response getMatch(long matchId) throws ResourceNotFoundException {

        Match match = matchRepository.findById(matchId).orElseThrow(() -> new ResourceNotFoundException("match is not found for this match id:: " + matchId));
        /* get userIds */
        List<Long> userIds = new ArrayList<>();
        match.getUserMatches().forEach(userMatch -> {
            userIds.add(userMatch.getUser().getId());
        });
        /* make MatchDTO Response */
        MatchDTO.Response response = new MatchDTO.Response(new MatchDTO.Info(match), userIds);

        return response;
    }
}
