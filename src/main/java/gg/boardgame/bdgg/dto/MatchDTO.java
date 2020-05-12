package gg.boardgame.bdgg.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchDTO {
    private int id;
    private int groupId;
    private Date playedTime;
    private String place;
    private int gameId;
    private int gameType;
    private int result;
    private List<IndividualGameResultDTO> playWith = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(Date playedTime) {
        this.playedTime = playedTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<IndividualGameResultDTO> getPlayWith() {
        return playWith;
    }

    public void setPlayWith(List<IndividualGameResultDTO> playWith) {
        this.playWith = playWith;
    }
}
