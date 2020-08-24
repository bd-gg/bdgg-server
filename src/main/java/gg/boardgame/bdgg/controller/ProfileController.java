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
}
