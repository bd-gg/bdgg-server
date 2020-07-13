package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.User;
import gg.boardgame.bdgg.db.UserRepository;
import gg.boardgame.bdgg.dto.OAuthDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Service
public class SignupServiceImpl implements SignupService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final KakaoAPI kakao;
    private final JwtService jwtService;

    public SignupServiceImpl(KakaoAPI kakao, JwtService jwtService) {
        this.kakao = kakao;
        this.jwtService = jwtService;
    }

    @Override
    public OAuthDTO createUser(OAuthDTO OAuthInfo) {
        /* get kakao user info */
        HashMap<String, Object> userInfo = kakao.getUserInfo(OAuthInfo.getAccessToken());
        Optional<User> retUser = userRepository.findById(Long.parseLong(userInfo.get("id").toString()));
        if(retUser.isPresent()) {
            log.warn("User already exists");
            return null;
        }
        /* save db */
        User user = User.builder()
                .id(Long.parseLong(userInfo.get("id").toString()))
                .name(userInfo.get("nickname").toString())
                .password(passwordEncoder.encode("pass1"))
                .email(userInfo.get("email").toString())
                .imageUrl(userInfo.get("profile").toString()).build();
        userRepository.save(user);

        /* issue jwt token */
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        String accessToken = new String();
        String refreshToken = new String();
        try {
            accessToken = jwtService.makeJwt(userInfo,60*60); // 1 hour
            refreshToken = jwtService.makeJwt(userInfo,60*60*24*7*2); // 2 weeks
        } catch (Exception e) {
            e.printStackTrace();
        }
        OAuthDTO dto = new OAuthDTO();
        dto.setAccessToken(accessToken);
        dto.setRefreshToken(refreshToken);

        return dto;
    }
}
