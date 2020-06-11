package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.db.User;
import gg.boardgame.bdgg.db.UserRepository;
import gg.boardgame.bdgg.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SignupServiceImpl implements SignupService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userInfo) {
        Optional<User> retUser = userRepository.findByEmail(userInfo.getEmail());
        if(retUser.isPresent()) {
            log.warn("Email already exists");
            return null;
        }

        User user = User.builder().id(userInfo.getId())
                .email(userInfo.getEmail())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .name(userInfo.getName())
                .imageUrl(userInfo.getImageUrl()).build();

        user = userRepository.save(user);
        userInfo.setId(user.getId());

        return userInfo;
    }
}
