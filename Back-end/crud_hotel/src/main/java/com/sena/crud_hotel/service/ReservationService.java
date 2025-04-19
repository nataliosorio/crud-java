package com.sena.crud_hotel.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.CreateReservationRequest;
import com.sena.crud_hotel.interfaces.ICustomer;
import com.sena.crud_hotel.interfaces.IEmployee;
import com.sena.crud_hotel.interfaces.IReservation;
import com.sena.crud_hotel.interfaces.IReservationRoom;
import com.sena.crud_hotel.interfaces.IRoom;
import com.sena.crud_hotel.model.Customer;
import com.sena.crud_hotel.model.Employee;
import com.sena.crud_hotel.model.EnumReservation;
import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.model.ReservationRoom;
import com.sena.crud_hotel.model.Room;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {

     @Autowired
    private IReservation reservationRepository;

    @Autowired
    private ICustomer customerRepository;

    @Autowired
    private IEmployee employeeRepository;

    @Autowired
    private IRoom roomRepository;

    @Autowired
    private IReservationRoom reservationRoomRepository;

    @Autowired
    private InvoiceService invoiceService;

    // Método para crear una nueva reserva
    @Transactional
    public Reservation createReservation(CreateReservationRequest dto) {
        // 1. Validar cliente y empleado
        Customer customer = customerRepository.findById(dto.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // 2. Crear la reserva
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setEmployee(employee);
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setStatus(EnumReservation.PENDING); // Por defecto, el estado es pendiente
        reservation.setNotes(dto.getNotes());
        reservation.setCreatedAt(LocalDateTime.now());

        reservation = reservationRepository.save(reservation);

        // 3. Asociar habitaciones y calcular precios
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Integer roomId : dto.getRoomIds()) {
            Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

            ReservationRoom reservationRoom = new ReservationRoom();
            reservationRoom.setReservation(reservation);
            reservationRoom.setRoom(room);

            // Precio aplicado puede ser por noche, por ejemplo:
            BigDecimal appliedPrice = room.getRoomType().getPriceNight(); // o getPriceDay()
            reservationRoom.setAppliedPrice(appliedPrice);

            reservationRoomRepository.save(reservationRoom);
            subtotal = subtotal.add(appliedPrice);
        }

        // 4. Generar factura usando el servicio especializado
        invoiceService.generateInvoice(reservation, subtotal);

        return reservation;
    }

    // Método para consultar todas las reservas
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    // Método para consultar una reserva por ID
    public Reservation findReservationById(int id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

   

    // Método para actualizar una reserva
    @Transactional
    public Reservation updateReservation(int id, CreateReservationRequest dto) {
        // 1. Validar si la reserva existe
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // 2. Actualizar los valores de la reserva
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setNotes(dto.getNotes());

        // 3. Actualizar habitaciones asociadas si se pasan nuevos IDs
        if (dto.getRoomIds() != null && !dto.getRoomIds().isEmpty()) {
            reservationRoomRepository.deleteById(id); // Eliminar habitaciones antiguas

            BigDecimal subtotal = BigDecimal.ZERO;
            for (Integer roomId : dto.getRoomIds()) {
                Room room = roomRepository.findById(roomId)
                        .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

                ReservationRoom reservationRoom = new ReservationRoom();
                reservationRoom.setReservation(reservation);
                reservationRoom.setRoom(room);

                // Precio aplicado puede ser por noche, por ejemplo:
                BigDecimal appliedPrice = room.getRoomType().getPriceNight(); // o getPriceDay()
                reservationRoom.setAppliedPrice(appliedPrice);

                reservationRoomRepository.save(reservationRoom);
                subtotal = subtotal.add(appliedPrice);
            }

            // 4. Generar nueva factura con los nuevos valores
            invoiceService.generateInvoice(reservation, subtotal);
        }

        return reservationRepository.save(reservation);
    }

    // Método para eliminar una reserva
    @Transactional
    public void deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Eliminar las habitaciones asociadas
        reservationRoomRepository.deleteById(id);

        // Eliminar la reserva
        reservationRepository.delete(reservation);
    }
}
