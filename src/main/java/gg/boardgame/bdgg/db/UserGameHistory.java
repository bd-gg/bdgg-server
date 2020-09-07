package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "USER_GAME_HISTORY")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameHistory {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "game_id")
    private long gameId;

    @Column(name = "count")
    private int count;

    @Builder
    public UserGameHistory(long id, int count) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(count, "count must be not null");
        this.id = id;
        this.count = count;
    }

    public void changeUser(User user) {
        this.user = user;
        user.getGameHistories().add(this);
    }
}
