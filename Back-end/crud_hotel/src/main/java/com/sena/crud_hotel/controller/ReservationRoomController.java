package com.sena.crud_hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.model.Room;
import com.sena.crud_hotel.service.ReservationRoomService;
@RestController
@RequestMapping("api/v1/ReservationRoom")
public class ReservationRoomController {
    
    @Autowired
    private ReservationRoomService reservationRoomService;


    @GetMapping("/{id}")
    public ResponseEntity<List<Room>> getRoomsByReservation(@PathVariable Integer id) {
        List<Room> rooms = reservationRoomService.getRoomsByReservation(id);
        return ResponseEntity.ok(rooms);
    }

    // Eliminar asociación habitación-reserva
    @DeleteMapping("/{reservationRoomId}")
    public ResponseEntity<Void> deleteReservationRoom(@PathVariable int reservationRoomId) {
        reservationRoomService.deleteReservationRoom(reservationRoomId);
        return ResponseEntity.noContent().build();
    }
    
}
