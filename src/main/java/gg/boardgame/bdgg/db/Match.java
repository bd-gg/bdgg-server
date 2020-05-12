package gg.boardgame.bdgg.db;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
public class Match {
    @Id @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;
    @Column(name="game_no")
    private int gameNo;
    @Column(name="game_type")
    private int gameType;
    @Column(name="play_time")
    private int playTime;

    @OneToMany(mappedBy = "match")
    private List<UserMatch> userMatches = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
