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

    @Column(name="game_no")
    private int gameNo;
    @Column(name="game_type")
    private int gameType;
    @Column(name="played_time")
    private Timestamp playedTime;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> userMatches = new ArrayList<>();

    @Builder
    public Match(long id, int gameNo, int gameType, Timestamp playedTime) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(gameNo, "gameNo must be not null");
        Assert.notNull(gameType, "gameType must be not null");
        Assert.notNull(playedTime, "playedTime must be not null");
        this.id = id;
        this.gameNo = gameNo;
        this.gameType = gameType;
        this.playedTime = playedTime;
    }

}
