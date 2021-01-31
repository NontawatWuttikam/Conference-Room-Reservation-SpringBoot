package com.cpe.conferenceRoomReserveApp.service;

import java.util.List;

import com.cpe.conferenceRoomReserveApp.entity.Building;
import com.cpe.conferenceRoomReserveApp.repository.BuildingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Building> getAll() {
        return buildingRepository.findAll();
    }

    public List<Building> getByBranchID(Long branchID) {
        return buildingRepository.findByBranchID(branchID);
    }
}
