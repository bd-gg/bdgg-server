package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.db.User;
import gg.boardgame.bdgg.dto.GameHistoryDTO;
import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody ProfileDTO getProfile(@PathVariable("userId") Integer userId, Pageable pageable) {
        logger.info(String.format("userId: %s", userId));

        // call service
        return profileService.getProfile(userId, pageable);
    }

    @RequestMapping(path = "/{userId}/matches", method = RequestMethod.GET)
    public @ResponseBody List<MatchDTO> getMatchListOfUser(@PathVariable("userId") Integer userId,
                           @RequestParam(value = "gameId", required = false) Integer gameId,
                           @RequestParam(value = "gameType", required = false) Integer gameType,
                           Pageable pageable) {
        logger.info(String.format("userId: %s, gameId: %d, gameType: %d", userId, gameId, gameType));

        // call service
        return profileService.getMatchList(userId, gameId, gameType, pageable);
    }
}
