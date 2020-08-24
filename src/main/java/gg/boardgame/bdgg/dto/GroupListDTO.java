package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupListDTO {
    private List<GroupDTO> items;

    public void setItems(List<Group> groupList) {
        if(items == null)
            items = new ArrayList<>();

        for(Group group : groupList) {
            GroupDTO gdto = new GroupDTO();
            gdto.copyFromGroupDO(group);
            items.add(gdto);
        }
    }
}
