package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@Entity
@Table(name = "RESERVATION")
@Data
public class Reservation {
    @GeneratedValue
    @Id
    @Column(name = "RESERVATIONID")
    private long reservationID;

    @Column(name = "ROOMID")
    private long roomID;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RESERVERID")
    private long reserverID;

    @Column(name = "STARTDATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @ManyToOne
    @JsonProperty("reserver")
    @JoinColumn(name = "RESERVERID", insertable = false, updatable = false)
    private User reserver;

    @ManyToOne
    @JsonProperty("room")
    @JoinColumn(name = "ROOMID", insertable = false, updatable = false)
    private Room room;

    @Column(name = "ENDDATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    public long getReservationID() {
        return this.reservationID;
    }

    public void setReservationID(long reservationID) {
        this.reservationID = reservationID;
    }

    public long getRoomID() {
        return this.roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

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

    public long getReserverID() {
        return this.reserverID;
    }

    public void setReserverID(long reserverID) {
        this.reserverID = reserverID;
    }

    public String getStartDateTime() {
        //2564-01-04T09:00:31.000+07:00
        String hour = (String.valueOf(this.startDateTime.getHours()).length() == 1)? "0" + String.valueOf(this.startDateTime.getHours()) : String.valueOf(this.startDateTime.getHours());
        String min = (String.valueOf(this.startDateTime.getMinutes()).length() == 1)? "0" + String.valueOf(this.startDateTime.getMinutes()) : String.valueOf(this.startDateTime.getMinutes());
        String sec = (String.valueOf(this.startDateTime.getSeconds()).length() == 1)? "0" + String.valueOf(this.startDateTime.getSeconds()) : String.valueOf(this.startDateTime.getSeconds());
        String month_i = String.valueOf(this.startDateTime.getMonth() + 1);
        String day_i = String.valueOf(this.startDateTime.getDate());
        String year_i = String.valueOf(this.startDateTime.getYear());
        String month = (month_i.length() == 1)? "0" + month_i : month_i;
        String day = (day_i.length() == 1)? "0" + day_i : day_i;
        String year = String.valueOf(Integer.parseInt(year_i) + 1900);

        return year+"-"+month+"-"+day+"T"+hour+":"+min+":"+sec+".000+07:00";
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        //2564-01-04T09:00:31.000+07:00
        String hour = (String.valueOf(this.endDateTime.getHours()).length() == 1)? "0" + String.valueOf(this.endDateTime.getHours()) : String.valueOf(this.endDateTime.getHours());
        String min = (String.valueOf(this.endDateTime.getMinutes()).length() == 1)? "0" + String.valueOf(this.endDateTime.getMinutes()) : String.valueOf(this.endDateTime.getMinutes());
        String sec = (String.valueOf(this.endDateTime.getSeconds()).length() == 1)? "0" + String.valueOf(this.endDateTime.getSeconds()) : String.valueOf(this.endDateTime.getSeconds());
        String month_i = String.valueOf(this.endDateTime.getMonth() + 1);
        String day_i = String.valueOf(this.endDateTime.getDate());
        String year_i = String.valueOf(this.endDateTime.getYear());
        String month = (month_i.length() == 1)? "0" + month_i : month_i;
        String day = (day_i.length() == 1)? "0" + day_i : day_i;
        String year = String.valueOf(Integer.parseInt(year_i) + 1900);

        return year+"-"+month+"-"+day+"T"+hour+":"+min+":"+sec+".000+07:00";
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public User getReserver() {
        return this.reserver;
    }

    public void setReserver(User reserver) {
        this.reserver = reserver;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
