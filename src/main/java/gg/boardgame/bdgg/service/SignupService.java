package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.dto.OAuthDTO;

public interface SignupService {
    public OAuthDTO createUser(OAuthDTO userInfo);
}
