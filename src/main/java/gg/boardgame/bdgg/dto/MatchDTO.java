package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.*;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import gg.boardgame.bdgg.service.ProfileService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MatchDTO {
    private long id;
    private int gameId;
    private int gameType;
    private Date playedTime;
    private String place;
    private String result;
    private String winnerUserName;
    private long winnerScore;
    private List<Long> userIds = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMatchRepository userMatchRepository;

    public MatchDTO(Match match) throws ResourceNotFoundException {
        this.id = match.getId();
        this.gameId = match.getGameId();
        this.gameType = match.getGameType();
        this.playedTime = match.getPlayedTime();
        this.place = match.getPlace();
        this.result = match.getResult();

        long winnerId = match.getWinnerId();
        /* 경쟁 게임이 아닌 협동 게임일 경우, 임의의 winner 객체 생성 */
        User winner = userRepository.findById(winnerId).orElseGet(() -> new User("no","no","no","no","no","no"));
        this.winnerUserName = winner.getName();

        /* get userIds & winnerScore */
        match.getUserMatches().forEach(userMatch -> {
            userIds.add(userMatch.getUser().getId());
            if (userMatch.getUser().equals(winner)) {
                this.winnerScore = userMatch.getScore();
            }
        });

    }
}
