package com.cpe.conferenceRoomReserveApp.service;

import java.util.List;

import javax.transaction.Transactional;

import com.cpe.conferenceRoomReserveApp.entity.Reservation;
import com.cpe.conferenceRoomReserveApp.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getReservationByRoom(Long roomID) {
        return reservationRepository.findByRoomID(roomID);
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
