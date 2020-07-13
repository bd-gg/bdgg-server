package gg.boardgame.bdgg.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuthDTO {
    private String nativeKey;
    private String accessToken;
    private String refreshToken;
}

