package gg.boardgame.bdgg.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchDTO {
    private long id;
    private long groupId;
    private Date playedTime;
    private String place;
    private int gameNo;
    private int gameType;
    private int result;
    private List<IndividualGameResultDTO> playWith = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
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

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
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
