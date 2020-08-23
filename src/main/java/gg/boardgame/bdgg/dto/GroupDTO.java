package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import io.jsonwebtoken.lang.Assert;
import lombok.Data;

@Data
public class GroupDTO {
    private Long id;
    private String image;
    private String name;
    private String place;

    public GroupDTO(Long id, String image, String name, String place) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.place = place;
    }

    public void copyFromGroupDO(Group g) {
        this.id = g.getId();
        this.image = g.getGroupImage();
        this.name = g.getGroupName();
        this.place = g.getGroupPlace();
    }
}
