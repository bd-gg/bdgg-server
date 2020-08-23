package gg.boardgame.bdgg.db;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GROUP_IMAGE")
    private String groupImage;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "GROUP_PLACE")
    private String groupPlace;

    @Column(name = "GROUP_ENTER_PASSWORD")
    private String groupEnterPassword;

    @Column(name = "GROUP_LEADER")
    private long groupLeader;

    @Column(name = "INVENTORY")
    private String inventory;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Match> matches = new ArrayList<>();

    @Builder
    public Group(long id, String groupImage, String groupName, String groupPlace, String groupEnterPassword, long groupLeader, String inventory) {
        Assert.notNull(id, "id must be not null");
        Assert.notNull(groupName, "groupName must be not null");
        Assert.notNull(groupPlace, "groupPlace must be not null");
        Assert.notNull(groupEnterPassword, "groupEnterPassword must be not null");
        Assert.notNull(groupLeader, "groupLeader must be not null");
        this.id = id;
        this.groupImage = groupImage; // this can be null;
        this.groupName = groupName;
        this.groupPlace = groupPlace;
        this.groupEnterPassword = groupEnterPassword;
        this.groupLeader = groupLeader;
        /* inventory can be bull */
        this.inventory = inventory;
    }

}
