package com.cpe.conferenceRoomReserveApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BUILDING")
public class Building {

    @Id
    @Column(name = "BUILDINGID")
    private Long buildingID;

    @Column(name = "BUILDINGNAME")
    private String buildingName;

    @Column(name = "BRANCHID")
    private Long branchID;

    @ManyToOne
    @JsonProperty("branch")
    @JoinColumn(name = "BRANCHID", insertable = false, updatable = false)
    private Branch branch;

    public Long getBuildingID() {
        return this.buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getBranchID() {
        return this.branchID;
    }

    public void setBranchID(Long branchID) {
        this.branchID = branchID;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
