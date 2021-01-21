package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROOM")
@Data
public class Room {
    @GeneratedValue
    @Id
    @Column(name = "ROOMID")
    private long roomID;

    @Column(name = "ROOMNAME")
    private String roomName;

    @Column(name = "BUILDING")
    private String building;

    @Column(name = "FLOOR")
    private int floor;

    @Column(name = "BRANCHID")
    private long branchID;

    @Column(name = "ROOMNO")
    private int roomNo;
}
