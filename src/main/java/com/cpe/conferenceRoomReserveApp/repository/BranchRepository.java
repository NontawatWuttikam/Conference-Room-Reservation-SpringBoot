package com.cpe.conferenceRoomReserveApp.repository;

import com.cpe.conferenceRoomReserveApp.entity.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
