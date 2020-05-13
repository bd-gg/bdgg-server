package gg.boardgame.bdgg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MatchDTO {
    private int id;
    private int groupId;
    private Date playedTime;
    private String place;
    private int gameId;
    private int gameType;
    private int result;
    private List<IndividualGameResultDTO> playWith = new ArrayList<>();
}
