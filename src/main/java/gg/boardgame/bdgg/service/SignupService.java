package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.UserDTO;

public interface SignupService {
    public UserDTO createUser(UserDTO userInfo);
}
