package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import io.jsonwebtoken.lang.Assert;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupDTO {
    private Long id;
    private String image;
    private String name;
    private String place;
    private List<Long> members;

    public GroupDTO(Long id, String image, String name, String place, List<Long> members) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.place = place;
        this.members = members;
    }

    public void copyFromGroupDO(Group g) {
        this.id = g.getId();
        this.image = g.getGroupImage();
        this.name = g.getGroupName();
        this.place = g.getGroupPlace();

        members = new ArrayList<>();
        g.getGroupMembers().stream().forEach(x -> members.add(x.getId()));
    }
}
