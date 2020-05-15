package gg.boardgame.bdgg.dto;

import lombok.Data;

@Data
public class IndividualGameResultDTO {
    private long userId;
    private String name;
    private int result;
}
