package com.cpe.conferenceRoomReserveApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STAFF")
@Data
public class Staff {

    @Id
    @Column(name = "STAFFID")
    private Long staffID;

    @Column(name = "NAME")
    private String staffName;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "PWDHASH")
    private String pwdHash;

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPwdHash() {
        return this.pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

}
