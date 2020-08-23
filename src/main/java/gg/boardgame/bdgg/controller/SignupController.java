package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.OAuthDTO;
import gg.boardgame.bdgg.dto.UserDTO;
import gg.boardgame.bdgg.service.JwtService;
import gg.boardgame.bdgg.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/signup")
public class SignupController {

    private final JwtService jwtService;
    private final SignupService signupService;

    SignupController(JwtService jwtService, SignupService signupService) {
        this.jwtService = jwtService;
        this.signupService = signupService;
    }

    @PostMapping()
    public OAuthDTO createUser(@RequestBody OAuthDTO oauth) {
        log.info("createUser is called");

        OAuthDTO retOAuth = signupService.createUser(oauth);

        // should handle exception
        return retOAuth;
    }
}
