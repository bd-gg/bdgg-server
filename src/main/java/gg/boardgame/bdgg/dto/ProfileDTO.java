package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO extends UserDTO{
    private List<GameHistoryDTO> mostPlayed = new ArrayList<>();

    public ProfileDTO(User user) {
        super(user);
    }
}
