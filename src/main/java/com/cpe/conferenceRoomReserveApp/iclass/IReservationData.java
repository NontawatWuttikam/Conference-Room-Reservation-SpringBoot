package com.cpe.conferenceRoomReserveApp.iclass;

import java.util.List;

public class IReservationData {

    private String title;
    private String description;
    private String room;
    private String userId;
    private List<String> startDateTime;
    private List<String> endDateTime;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String branch) {
        this.room = branch;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public List<String> getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(List<String> startDateTime) {
        this.startDateTime = startDateTime;
    }

    public List<String> getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(List<String> endDateTime) {
        this.endDateTime = endDateTime;
    }

}
