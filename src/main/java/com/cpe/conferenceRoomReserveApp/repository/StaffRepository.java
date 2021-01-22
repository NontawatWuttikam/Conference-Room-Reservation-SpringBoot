package com.cpe.conferenceRoomReserveApp.repository;

import java.util.List;

import com.cpe.conferenceRoomReserveApp.entity.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    public List<Staff> findByStaffName(String name);
}
