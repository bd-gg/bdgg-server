package gg.boardgame.bdgg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MatchDTO {
    private long id;
    private long groupId;
    private Date playedTime;
    private String place;
    private int gameNo;
    private int gameType;
    private int result;
    private List<IndividualGameResultDTO> playWith = new ArrayList<>();
}