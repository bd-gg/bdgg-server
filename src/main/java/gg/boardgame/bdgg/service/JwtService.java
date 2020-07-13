package gg.boardgame.bdgg.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface JwtService {
    boolean checkJwt(String jwt) throws Exception;
    String makeJwt(HashMap<String, Object> userInfo, long time) throws Exception;
}
