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
@Data
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

}
