package gg.boardgame.bdgg.dto;

import lombok.Data;

@Data
public class IndividualGameResultDTO {
    private Long userId;
    private String name;
    private Long result;
}
