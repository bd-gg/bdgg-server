package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class MatchDTO {

    public MatchDTO(Match match) {
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info {

        public Info(Match match) {
            this.id = match.getId();
            this.gameId = match.getGameId();
            this.gameType = match.getGameType();
            this.playedTime = match.getPlayedTime();
            this.place = match.getPlace();
            this.result = match.getResult();
            this.winnerId = match.getWinnerId();
        }

        private Long id;
        private Long gameId;
        private Integer gameType;
        private Timestamp playedTime;
        private String place;
        private String result;
        private Long winnerId;
    }

    @Getter
    @Setter
    public static class Request {
        private Long gameId;
        private Integer gameType;
        private Timestamp playedTime;
        private String place;
        private String result;
        private Long winnerId;
        private List<Pair<Long,Long>> userScores = new ArrayList<>();
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Info info;
        private List<Long> userIds = new ArrayList<>();

        public void setUserIds(List<Long> userIds) {
            this.userIds = userIds;
        }
    }

}
