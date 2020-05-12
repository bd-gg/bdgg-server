package gg.boardgame.bdgg.db;

import javax.persistence.*;

@Entity
public class UserMatch {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") //child 에 지정되어 있는 FK user_id 기준으로 user 조회
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id") //child 에 지정되어 있는 FK match_id 기준으로 match 조회
    private Match match;

    @Column(name = "score")
    private int score;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
