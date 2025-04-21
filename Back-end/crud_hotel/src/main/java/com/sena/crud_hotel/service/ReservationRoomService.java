package com.sena.crud_hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.crud_hotel.interfaces.IReservationRoom;
import com.sena.crud_hotel.model.Room;


@Service
public class ReservationRoomService {

    private final IReservationRoom reservationRoomRepository;

    public ReservationRoomService(IReservationRoom reservationRoomRepository) {
        this.reservationRoomRepository = reservationRoomRepository;
    }

   
    public List<Room> getRoomsByReservation(Integer reservationId) {
        return reservationRoomRepository.findRoomsByReservationId(reservationId);
    }

    // Eliminar habitación de una reserva
    public void deleteReservationRoom(int reservationRoomId) {
        reservationRoomRepository.deleteById(reservationRoomId);
    }
}
