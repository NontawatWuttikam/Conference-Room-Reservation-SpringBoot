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

    public long getRoomID() {
        return this.roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuilding() {
        return this.building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getBranchID() {
        return this.branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public int getRoomNo() {
        return this.roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

}
