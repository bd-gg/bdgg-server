package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.MatchDTO;
import gg.boardgame.bdgg.dto.ProfileDTO;
import gg.boardgame.bdgg.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody ProfileDTO getProfile(@PathVariable("userId") String userId) {
        logger.info(String.format("userId: %s", userId));
        // call service
        return new ProfileDTO();
    }

    @RequestMapping(path = "/{userId}/matches", method = RequestMethod.GET)
    public @ResponseBody List<MatchDTO> getMatchListOfUser(@PathVariable("userId") String userId,
                                      @RequestParam(value = "gameId", required = false) Integer gameId,
                                      @RequestParam(value = "gameType", required = false) Integer gameType) {
        logger.info(String.format("userId: %s, gameId: %d, gameType: %d", userId, gameId, gameType));

        // call service
        return new ArrayList<>();
    }
}
