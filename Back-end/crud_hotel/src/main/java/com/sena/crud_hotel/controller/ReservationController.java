package com.sena.crud_hotel.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.sena.crud_hotel.interfaces.IReservationRoom;
import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.model.ReservationRoom;
import com.sena.crud_hotel.service.ReservationService;

@RestController
@RequestMapping("api/v1/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private IReservationRoom reservationRoomRepository;

    // Obtener todas las reservas
    @GetMapping("/")
    public ResponseEntity<List<requestReservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        List<requestReservation> dtoList = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            requestReservation dto = mapToDto(reservation);
            dtoList.add(dto);
        }
        
        return ResponseEntity.ok(dtoList);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<requestReservation> getReservationById(@PathVariable int id) {
        Reservation reservation = reservationService.findReservationById(id);
        requestReservation dto = mapToDto(reservation);
        return ResponseEntity.ok(dto);
    }

    // Crear una nueva reserva
    @PostMapping("/")
    public ResponseEntity<requestReservation> createReservation(@RequestBody CreateReservationRequest request) {
        Reservation newReservation = reservationService.createReservation(request);
        
        // Obtener las habitaciones de la reservación para incluirlas en el DTO
        List<ReservationRoom> reservationRooms = reservationRoomRepository.findByReservationId(newReservation.getId());
        
        requestReservation dto = mapToDto(newReservation, reservationRooms);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // Actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<requestReservation> updateReservation(
            @PathVariable int id,
            @RequestBody CreateReservationRequest request) {
        Reservation updatedReservation = reservationService.updateReservation(id, request);
        
        // Obtener las habitaciones actualizadas
        List<ReservationRoom> reservationRooms = reservationRoomRepository.findByReservationId(updatedReservation.getId());
        
        requestReservation dto = mapToDto(updatedReservation, reservationRooms);
        return ResponseEntity.ok(dto);
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
    
    // Método para mapear Reservation a requestReservation
    private requestReservation mapToDto(Reservation reservation) {
        List<ReservationRoom> reservationRooms = reservationRoomRepository.findByReservationId(reservation.getId());
        return mapToDto(reservation, reservationRooms);
    }
    
    // Método para mapear Reservation a requestReservation con habitaciones específicas
    private requestReservation mapToDto(Reservation reservation, List<ReservationRoom> reservationRooms) {
        requestReservation dto = new requestReservation();
        
        // Mapear datos básicos
        dto.setId(reservation.getId());
        dto.setCustomerId(reservation.getCustomer().getId());
        dto.setCustomerFullName(reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName());
        dto.setEmployeeId(reservation.getEmployee().getId());
        dto.setEmployeeFullName(reservation.getEmployee().getFirstName() + " " + reservation.getEmployee().getLastName());
        dto.setNumberday(reservation.getNumberday());
        dto.setNumberNight(reservation.getNumberNight());
        dto.setStatus(reservation.getStatus().toString());
        dto.setNotes(reservation.getNotes());
        dto.setCreatedAt(reservation.getCreatedAt());
        
        // Mapear las habitaciones
        List<RequestReservationRoom> roomDtos = new ArrayList<>();
        for (ReservationRoom resRoom : reservationRooms) {
            RequestReservationRoom roomDto = new RequestReservationRoom();
            roomDto.setId(resRoom.getId());
            roomDto.setRoomId(resRoom.getRoom().getId());
            roomDto.setRoomNumber(resRoom.getRoom().getRoomNumber());
            roomDto.setAppliedPrice(resRoom.getAppliedPrice());
            roomDtos.add(roomDto);
        }
        dto.setRooms(roomDtos);
        
        // Mapear la factura
        Invoice invoice = reservation.getInvoice();
        if (invoice != null) {
            RequestInvoice invoiceDto = new RequestInvoice();
            invoiceDto.setId(invoice.getId());
            invoiceDto.setReservationId(reservation.getId());
            invoiceDto.setSubtotal(invoice.getSubtotal());
            invoiceDto.setTax(invoice.getTax());
            invoiceDto.setTotal(invoice.getTotal());
            invoiceDto.setPaymentStatus(invoice.getPaymentStatus());
            invoiceDto.setIssueDate(invoice.getIssueDate());
            dto.setInvoice(invoiceDto);
        }
        
        return dto;
    }
}
