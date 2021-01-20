package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class roomEntity {
@GeneratedValue
@Id
@Column(name = "roomID")
private long roomID;

@Column(name = "roomName")
private String roomName;

@Column(name = "building")
private String building;

@Column(name = "floor")
private int floor;

@Column(name = "branchID")
private long branchID;

@Column(name = "roomNo")
private int roomNo;
}
