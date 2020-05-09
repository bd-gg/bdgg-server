package gg.boardgame.bdgg.example.db;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MATCHES")
public class Match {
    @Id @GeneratedValue
    private long id;

    @Column(name="`GAME_NO`")
    private int gameNo;
    @Column(name="`GAME_TYPE`")
    private int gameType;
    @Column(name="`PLAY_TIME`")
    private int playTime;
    @Column(name="`WINNER`")
    private int winner;

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

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
