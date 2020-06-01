package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.UserDTO;
import gg.boardgame.bdgg.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    SignupService signupService;

    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO user) {
        log.info(String.format("userName: %s", user.getName()));

        UserDTO retUser = signupService.createUser(user);
        // should handle exception
        return retUser;
    }
}
