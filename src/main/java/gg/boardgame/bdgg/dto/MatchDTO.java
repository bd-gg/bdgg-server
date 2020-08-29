package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class MatchDTO {
    private Long id;
    private Long gameId;
    private Integer gameType;
    private Date playedTime;
    private String place;
    private String result;
    private Long winnerId;
    private List<Long> userIds = new ArrayList<>();

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.gameId = match.getGameId();
        this.gameType = match.getGameType();
        this.playedTime = match.getPlayedTime();
        this.place = match.getPlace();
        this.result = match.getResult();
        this.winnerId = match.getWinnerId();
        /* get userIds */
        match.getUserMatches().forEach(userMatch -> {
            this.userIds.add(userMatch.getUser().getId());
        });
    }
}
