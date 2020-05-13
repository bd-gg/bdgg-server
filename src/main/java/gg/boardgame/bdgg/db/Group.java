package gg.boardgame.bdgg.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Match> matches = new ArrayList<>();

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "GROUP_PLACE")
    private String groupPlace;

    @Column(name = "GROUP_ENTER_PASSWORD")
    private String groupEnterPassword;

    @Column(name = "GROUP_LEADER")
    private int groupLeader;

    @Column(name = "INVENTORY")
    private String inventory;
}
