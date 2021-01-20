package com.cpe.conferenceRoomReserveApp.repository;

import com.cpe.conferenceRoomReserveApp.entity.reservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reservationRepository extends JpaRepository<reservationEntity,Long> {

    @Query(value = "SELECT * FROM reservation WHERE roomID  =(select roomID from room where roomName = ?1)",nativeQuery = true)
    List<reservationEntity> findByName(String name);

}
