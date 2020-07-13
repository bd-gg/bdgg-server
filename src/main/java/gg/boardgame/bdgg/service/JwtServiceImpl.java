package gg.boardgame.bdgg.service;

import gg.boardgame.bdgg.config.ApplicationYmlManager;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.yml")
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final ApplicationYmlManager ymlManager;
    private Key KEY;
    private String secretKey;
    private byte[] secretBytes;
    private SignatureAlgorithm signatureAlgorithm;

    public JwtServiceImpl(ApplicationYmlManager ymlManager) {
        this.ymlManager = ymlManager;
        signatureAlgorithm = SignatureAlgorithm.HS256;
        secretKey = ymlManager.getSecret();
        secretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        KEY = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
    }

    @Override
    public String makeJwt(HashMap<String, Object> userInfo, long time) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + time);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

        String name = userInfo.get("nickname").toString();
        String email = userInfo.get("email").toString();

        map.put("name", name);
        map.put("email", email);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }


    @Override
    public boolean checkJwt(String jwt) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰

            log.info("expireTime :" + claims.getExpiration());
            log.info("name :" + claims.get("name"));
            log.info("Email :" + claims.get("email"));

            return true;
        } catch (ExpiredJwtException exception) {
            log.info("토큰 만료");
            return false;
        } catch (JwtException exception) {
            log.info("토큰 변조");
            return false;
        }
    }
}

