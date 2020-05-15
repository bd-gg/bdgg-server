package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "USER_MATCH")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMatch {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_id") //child 에 지정되어 있는 FK user_id 기준으로 user 조회
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id") //child 에 지정되어 있는 FK match_id 기준으로 match 조회
    private Match match;

    @Builder
    public UserMatch(long id, int score) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(score, "score must be not null");
        this.id = id;
        this.score = score;
    }

    public void changeUser(User user) {
        this.user = user;
        user.getUserMatches().add(this);
    }

    public void changeMatch(Match match) {
        this.match = match;
        match.getUserMatches().add(this);
    }
}
