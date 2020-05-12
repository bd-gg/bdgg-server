package gg.boardgame.bdgg.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {
    private int id;
    private String Name;
    private String imageUrl;
    private List<GameHistoryDTO> mostPlayed = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<GameHistoryDTO> getMostPlayed() {
        return mostPlayed;
    }

    public void setMostPlayed(List<GameHistoryDTO> mostPlayed) {
        this.mostPlayed = mostPlayed;
    }
}
