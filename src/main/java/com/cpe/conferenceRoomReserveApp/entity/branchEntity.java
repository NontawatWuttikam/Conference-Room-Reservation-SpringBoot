package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "branch")
@Data
public class branchEntity {

@Id
@GeneratedValue
@Column(name = "branchID")
private long branchID;

@Column(name = "branchName")
private String branchName;



















}
