package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BRANCH")
@Data
public class Branch {

    @Id
    @GeneratedValue
    @Column(name = "BRANCHID")
    private long branchID;

    @Column(name = "BRANCHNAME")
    private String branchName;

}
