package gg.boardgame.bdgg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class ApplicationYmlManager {
    private String secret;

    public String getSecret() {
        return secret;
    }
}
