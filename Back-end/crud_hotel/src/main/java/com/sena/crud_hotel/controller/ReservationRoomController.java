package com.sena.crud_hotel.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.model.ReservationRoom;
import com.sena.crud_hotel.service.ReservationRoomService;
@RestController
@RequestMapping("api/v1/ReservationRoom")
public class ReservationRoomController {

    private ReservationRoomService reservationRoomService;

  
    // Verificar disponibilidad de habitación
    @GetMapping("/availability")
    public ResponseEntity<Map<String, Boolean>> checkRoomAvailability(
            @RequestParam int roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOut) {
        
        boolean isAvailable = reservationRoomService.isRoomAvailable(roomId, checkIn, checkOut);
        Map<String, Boolean> response = Map.of("available", isAvailable);
        
        return ResponseEntity.ok(response);
    }

    // Obtener habitaciones por reserva
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<ReservationRoom>> getRoomsByReservation(@PathVariable int reservationId) {
        List<ReservationRoom> rooms = reservationRoomService.getRoomsByReservation(reservationId);
        return ResponseEntity.ok(rooms);
    }

    // Actualizar asociación habitación-reserva
    @PutMapping("/{reservationRoomId}")
    public ResponseEntity<Void> updateReservationRoom(
            @PathVariable int reservationRoomId,
            @RequestBody UpdateReservationRoomRequest request) {
        
        reservationRoomService.updateReservationRoom(
                reservationRoomId,
                request.getNewRoomId(),
                request.getNewPrice()
        );
        
        return ResponseEntity.ok().build();
    }

    // Eliminar asociación habitación-reserva
    @DeleteMapping("/{reservationRoomId}")
    public ResponseEntity<Void> deleteReservationRoom(@PathVariable int reservationRoomId) {
        reservationRoomService.deleteReservationRoom(reservationRoomId);
        return ResponseEntity.noContent().build();
    }

    // Clase para la solicitud de actualización
    public static class UpdateReservationRoomRequest {
        private int newRoomId;
        private BigDecimal newPrice;

        public int getNewRoomId() {
            return newRoomId;
        }

        public void setNewRoomId(int newRoomId) {
            this.newRoomId = newRoomId;
        }

        public BigDecimal getNewPrice() {
            return newPrice;
        }

        public void setNewPrice(BigDecimal newPrice) {
            this.newPrice = newPrice;
        }
    }
}
