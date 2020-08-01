package gg.boardgame.bdgg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class ApplicationYmlManager {
    private String secret;

    public String getSecret() {
        return secret;
    }
}
