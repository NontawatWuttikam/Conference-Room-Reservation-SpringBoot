package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "RESERVERID")
    private long reserverID;

    @Column(name = "STARTDATETIME")
    @Temporal(TemporalType.DATE)
    private Date startDateTime;

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

    public long getReserverID() {
        return this.reserverID;
    }

    public void setReserverID(long reserverID) {
        this.reserverID = reserverID;
    }

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Column(name = "ENDDATETIME")
    @Temporal(TemporalType.DATE)
    private Date endDateTime;

}
