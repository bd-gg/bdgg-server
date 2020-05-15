package gg.boardgame.bdgg.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Column(name="played_time")
    private Timestamp playedTime;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
