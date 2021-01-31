package com.cpe.conferenceRoomReserveApp.iclass;

public class IAddRoom {

    private String title;
    private String branchId;
    private String buildingId;
    private String floorNo;
    private String maxPeople;

    public String getMaxPeople() {
        return this.maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloorNo() {
        return this.floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

}
