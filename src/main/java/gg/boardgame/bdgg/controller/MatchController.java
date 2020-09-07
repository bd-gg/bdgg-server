package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(path = "/{matchId}", method = RequestMethod.GET)
    public ResponseEntity getMatch(@PathVariable("matchId") long matchId) throws ResourceNotFoundException {
        log.info("getProfile is called");
        log.info(String.format("matchId: %d", matchId));

        // call service
        MatchDTO.Response response = matchService.getMatch(matchId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

