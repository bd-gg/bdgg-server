package gg.boardgame.bdgg.example.db;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers = new ArrayList<>();

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPlace() {
        return groupPlace;
    }

    public void setGroupPlace(String groupPlace) {
        this.groupPlace = groupPlace;
    }

    public String getGroupEnterPassword() {
        return groupEnterPassword;
    }

    public void setGroupEnterPassword(String groupEnterPassword) {
        this.groupEnterPassword = groupEnterPassword;
    }

    public int getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(int groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
