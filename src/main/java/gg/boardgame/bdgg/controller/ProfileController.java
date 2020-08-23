package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.db.User;
import gg.boardgame.bdgg.dto.GameHistoryDTO;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable("userName") String userName, Pageable pageable) throws ResourceNotFoundException {
        log.info(String.format("userName: %s", userName));

        ProfileDTO profile = profileService.getProfile(userName, pageable);

        // call service
        return ResponseEntity.ok(profile);
    }

    @RequestMapping(path = "/{userId}/matches", method = RequestMethod.GET)
    public @ResponseBody List<MatchDTO> getMatchListOfUser(@PathVariable("userId") Integer userId,
                           @RequestParam(value = "gameId", required = false) Integer gameId,
                           @RequestParam(value = "gameType", required = false) Integer gameType,
                           Pageable pageable) {
        log.info(String.format("userId: %s, gameId: %d, gameType: %d", userId, gameId, gameType));

        // call service
        return profileService.getMatchList(userId, gameId, gameType, pageable);
    }
}
