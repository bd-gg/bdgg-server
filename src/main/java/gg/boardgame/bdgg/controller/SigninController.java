package gg.boardgame.bdgg.controller;

import gg.boardgame.bdgg.dto.KakaoAuthDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/sign-in")
public class SigninController {

    private final String kakaoOauthUri = "https://kauth.kakao.com/oauth/token";
    private final String redirectUri = "https://server.addr/sign-in/kakao/callback";
    private final String clientId = "741028e289ff41e4c8113d39787cb6bd";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/kakao/callback")
    public String signin(@RequestParam(value = "code", required = false) String code) {

        // get access token and refresh token from kakao server
        KakaoAuthDTO authResponse = getTokens(code);

        // check if the user signed up or not
        // get access token and refresh token of ours
        // Taehyun's code

        // return tokens
        return null;
    }

    private KakaoAuthDTO getTokens(String code) {

        Map<String, String> uriVars = new HashMap<>();
        uriVars.put("grant_type",   "authorization_code");
        uriVars.put("client_id",    this.clientId);
        uriVars.put("redirect_uri", this.redirectUri);
        uriVars.put("code",         code);

        KakaoAuthDTO authResponse;
        try {
            authResponse = restTemplate.getForEntity(this.kakaoOauthUri, KakaoAuthDTO.class, uriVars).getBody();
        } catch(RestClientException e) {
            e.printStackTrace();
            // handle error
            authResponse = null;
        }

        return authResponse;
    }

}
