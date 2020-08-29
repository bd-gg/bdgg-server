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
    private List<MatchDTO> items = new ArrayList<>();

    public void setItems(List<Match> matchList) {
        for(Match match : matchList) {
            MatchDTO mdto = new MatchDTO(match);
            items.add(mdto);
        }
    }
}
