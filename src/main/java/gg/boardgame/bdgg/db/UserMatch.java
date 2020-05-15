package gg.boardgame.bdgg.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_MATCH")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
