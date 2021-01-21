package com.cpe.conferenceRoomReserveApp.repository;

import com.cpe.conferenceRoomReserveApp.entity.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
