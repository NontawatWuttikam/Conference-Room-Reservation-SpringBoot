package com.cpe.conferenceRoomReserveApp.repository;

import java.util.List;

import com.cpe.conferenceRoomReserveApp.entity.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByBranchID(Long branchID);
}
