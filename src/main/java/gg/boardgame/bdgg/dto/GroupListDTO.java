package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.Group;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupListDTO {
    private List<GroupDTO> items;
}
