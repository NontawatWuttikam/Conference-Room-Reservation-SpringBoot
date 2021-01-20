package com.cpe.conferenceRoomReserveApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class reservationEntity {
@GeneratedValue
@Id
@Column(name = "reservationID")
private long reservationID;

@Column(name = "roomID")
private long roomID;

@Column(name = "reserverID")
private long reserverID;

@Column(name = "startDateTime")
@Temporal(TemporalType.DATE)
private Date startDateTime;

@Column(name = "endDateTime")
@Temporal(TemporalType.DATE)
private Date endDateTime;














}
