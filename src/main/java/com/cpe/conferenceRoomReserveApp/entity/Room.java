package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;

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

    @Column(name = "BUILDINGID")
    private Long buildingID;

    @ManyToOne
    @JsonProperty("building")
    @JoinColumn(name = "BUILDINGID", insertable = false, updatable = false)
    private Building building;

    @Column(name = "FLOOR")
    private int floor;

    @Column(name = "ROOMNO")
    private int roomNo;

    public Building getBuilding() {
        return this.building;
    }

    public void setBuilding(Building branch) {
        this.building = branch;
    }

    public Branch getBranch() {
        return this.building.getBranch();
    }

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

    public Long getBuildingId() {
        return this.buildingID;
    }

    public void setBuildingId(Long buildingID) {
        this.buildingID = buildingID;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getBranchID() {
        return this.building.getBranchID();
    }

    public void setBranchID(long branchID) {
        this.building.setBranchID(branchID);
    }

    public int getRoomNo() {
        return this.roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

}
