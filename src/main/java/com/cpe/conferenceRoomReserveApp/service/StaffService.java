package com.cpe.conferenceRoomReserveApp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import com.cpe.conferenceRoomReserveApp.entity.Staff;
import com.cpe.conferenceRoomReserveApp.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public Optional<Staff> getStaffByName(String name) {
        List<Staff> staffs = staffRepository.findByStaffName(name);
        if (staffs.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(staffs.get(0));
    }
}
