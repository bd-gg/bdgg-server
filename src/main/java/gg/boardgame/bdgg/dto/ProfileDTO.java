package gg.boardgame.bdgg.dto;

import gg.boardgame.bdgg.db.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO {
    private long id;
    private String name;
    private String email;
    private String imageUrl;
    private List<GameHistoryDTO> mostPlayed = new ArrayList<>();

    public ProfileDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
