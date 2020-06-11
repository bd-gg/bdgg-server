package gg.boardgame.bdgg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/my")
    public String my() {
        return "my";
    }
}
