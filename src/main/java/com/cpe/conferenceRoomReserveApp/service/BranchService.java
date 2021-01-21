package com.cpe.conferenceRoomReserveApp.service;

import java.util.List;

import javax.transaction.Transactional;

import com.cpe.conferenceRoomReserveApp.entity.Branch;
import com.cpe.conferenceRoomReserveApp.repository.BranchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }
}
