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

    public long getBranchID() {
        return this.branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
