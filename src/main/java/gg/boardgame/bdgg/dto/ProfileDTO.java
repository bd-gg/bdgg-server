package gg.boardgame.bdgg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileDTO {
    private int id;
    private String Name;
    private String imageUrl;
    private List<GameHistoryDTO> mostPlayed = new ArrayList<>();
}
