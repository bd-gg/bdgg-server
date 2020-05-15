package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_MEMBER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupMember {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id") //child 에 지정되어 있는 FK user_id 기준으로 user 조회
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id") //child 에 지정되어 있는 FK match_id 기준으로 match 조회
    private Group group;

    @Builder
    public GroupMember(long id) {
        Assert.notNull(id, "id must be not null");
        this.id = id;
    }
}
