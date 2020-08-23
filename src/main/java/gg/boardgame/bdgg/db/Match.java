package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {
    @Id @GeneratedValue
    private long id;

    @Column(name="game_id")
    private int gameId;
    @Column(name="game_type")
    private int gameType;
    @Column(name="played_time")
    private Timestamp playedTime;
    @Column(name="place")
    private String place;
    @Column(name="winner_id")
    private long winnerId = -1;
    @Column(name="result")
    private String result;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> userMatches = new ArrayList<>();

    @Builder
    public Match(long id, int gameId, int gameType, Timestamp playedTime, String place) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(gameId, "gameId must be not null");
        Assert.notNull(gameType, "gameType must be not null");
        Assert.notNull(playedTime, "playedTime must be not null");
        Assert.notNull(place, "place must be not null");
        this.id = id;
        this.gameId = gameId;
        this.gameType = gameType;
        this.playedTime = playedTime;
        this.place = place;
    }

    public void changeGroup(Group group) {
        this.group = group;
        this.group.getMatches().add(this);
    }

}
