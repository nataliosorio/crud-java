package com.sena.crud_hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.DTO.CreateReservationRequest;
import com.sena.crud_hotel.DTO.RequestInvoice;
import com.sena.crud_hotel.DTO.RequestReservationRoom;
import com.sena.crud_hotel.DTO.requestReservation;
import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.service.ReservationService;

@RestController
@RequestMapping("api/v1/Reservation")
public class ReservationController {

   @Autowired
    private ReservationService reservationService;


// Obtener todas las reservas
@GetMapping("/")
public ResponseEntity<List<requestReservation>> getAllReservations() {
    List<Reservation> reservations = reservationService.findAllReservations();
    
    List<requestReservation> response = reservations.stream().map(reservation -> {
        List<RequestReservationRoom> roomDTOs = reservation.getReservationRooms().stream()
            .map(room -> new RequestReservationRoom(
                room.getRoom().getId(),
                room.getRoom().getRoomType().getName(),
                room.getAppliedPrice()
            ))
            .collect(Collectors.toList());

        RequestInvoice invoiceDTO = null;
        if (reservation.getInvoice() != null) {
            invoiceDTO = new RequestInvoice(
                reservation.getInvoice().getId(),
                reservation.getId(), 
                reservation.getInvoice().getSubtotal(),
                reservation.getInvoice().getTax(),
                reservation.getInvoice().getTotal(),
                reservation.getInvoice().getPaymentStatus(),
                reservation.getInvoice().getIssueDate()
            );
        }

        return new requestReservation(
            reservation.getId(),
            reservation.getCustomer().getId(),
            reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName(),
            reservation.getEmployee().getId(),
            reservation.getEmployee().getFirstName() + " " + reservation.getEmployee().getLastName(),
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            reservation.getStatus().toString(),
            reservation.getNotes(),
            reservation.getCreatedAt(),
            roomDTOs,
            invoiceDTO
        );
    }).collect(Collectors.toList());

    return ResponseEntity.ok(response);
}
    // Obtener todas las reservas
    // @GetMapping("/")
    // public ResponseEntity<List<Reservation>> getAllReservations() {
    //     List<Reservation> reservations = reservationService.findAllReservations();
    //     return ResponseEntity.ok(reservations);
    // }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        Reservation reservation = reservationService.findReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    // Crear una nueva reserva
    @PostMapping("/")
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationRequest request) {
        Reservation newReservation = reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    }

    // Actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable int id,
            @RequestBody CreateReservationRequest request) {
        Reservation updatedReservation = reservationService.updateReservation(id, request);
        return ResponseEntity.ok(updatedReservation);
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
