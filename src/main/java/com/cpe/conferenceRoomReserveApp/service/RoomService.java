package com.cpe.conferenceRoomReserveApp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.cpe.conferenceRoomReserveApp.entity.Room;
import com.cpe.conferenceRoomReserveApp.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }
}
