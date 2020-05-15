package gg.boardgame.bdgg.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_GAME_HISTORY")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameHistory {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "game_id")
    private int gameId;

    @Column(name = "count")
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
