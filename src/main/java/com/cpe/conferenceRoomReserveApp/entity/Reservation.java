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

    @Column(name = "ENDDATETIME")
    @Temporal(TemporalType.DATE)
    private Date endDateTime;

}
