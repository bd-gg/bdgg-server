package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.db.Match;
import gg.boardgame.bdgg.exception.ResourceNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class MatchListDTO {
    private List<MatchDTO.Response> items = new ArrayList<>();

    public void setItems(List<Match> matchList) {
        for(Match match : matchList) {
            /* get userIds */
            List<Long> userIds = new ArrayList<>();
            match.getUserMatches().forEach(userMatch -> {
                userIds.add(userMatch.getUser().getId());
            });
            /* make MatchDTO Response */
            MatchDTO.Response response = new MatchDTO.Response(new MatchDTO.Info(match), userIds);
            items.add(response);
        }
    }
}
