package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import gg.boardgame.bdgg.db.Match;
import io.jsonwebtoken.lang.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String image;
    private String name;
    private String place;
    private Long totalPlayCount;
    private List<Long> members = new ArrayList<>();
    private List<RecentlyPlayedGame> recentlyPlayedGames = new ArrayList<>();

    public void copyFromGroupDO(Group g) {
        this.id = g.getId();
        this.image = g.getGroupImage();
        this.name = g.getGroupName();
        this.place = g.getGroupPlace();

        g.getGroupMembers().stream().forEach(x -> members.add(x.getId()));
    }

    public void setRecentlyPlayedGames(List<Match> rpgList) {
        for(Match rpg: rpgList) {
            recentlyPlayedGames.add(new RecentlyPlayedGame(rpg.getGameId(), rpg.getPlayedTime()));
        }
    }

    @Data
    @AllArgsConstructor
    public static class RecentlyPlayedGame {
        private long gameId;
        private Date playedTime;
    }
}
