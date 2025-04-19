package com.sena.crud_hotel.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_hotel.DTO.CreateReservationRequest;
import com.sena.crud_hotel.DTO.RequestInvoice;
import com.sena.crud_hotel.DTO.RequestReservationRoom;
import com.sena.crud_hotel.model.Employee;
import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.model.PaymentStatus;
import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.model.ReservationRoom;
import com.sena.crud_hotel.model.Room;

import jakarta.persistence.EntityNotFoundException;

public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Crea una nueva reservación con sus habitaciones y factura
     */
    public RequestReservation createReservation(CreateReservationRequest dto) {
        // Validar fechas
        if (dto.getCheckInDate().isAfter(dto.getCheckOutDate())) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser posterior a la fecha de salida");
        }
        
        // Validar empleado
        Employee employee = employeeRepository.findById(dto.getIdEmployee())
            .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        
        // Crear reservación
        Reservation reservation = new Reservation();
        reservation.setClientName(dto.getClientName());
        reservation.setClientEmail(dto.getClientEmail());
        reservation.setClientPhone(dto.getClientPhone());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setNotes(dto.getNotes());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setEmployee(employee);
        
        // Agregar habitaciones a la reservación
        List<Room> rooms = roomRepository.findAllByIdIn(dto.getRoomIds());
        for (Room room : rooms) {
            ReservationRoom reservationRoom = new ReservationRoom();
            reservationRoom.setReservation(reservation);
            reservationRoom.setRoom(room);
            reservation.getReservationRooms().add(reservationRoom);
        }
        
        // Calcular factura
        Invoice invoice = calculateInvoice(reservation);
        reservation.setInvoice(invoice);
        invoice.setReservation(reservation);
        
        // Guardar reservación (y por cascada los reservation_rooms y la invoice)
        reservation = reservationRepository.save(reservation);
        
        // Convertir a DTO y retornar
        return convertToDTO(reservation);
    }
    
    /**
     * Calcula la factura basada en la duración de la estancia y los tipos de habitación
     */
    private Invoice calculateInvoice(Reservation reservation) {
        Invoice invoice = new Invoice();
        BigDecimal subtotal = BigDecimal.ZERO;
        
        // Determinar duración en días y horas
        long totalHours = ChronoUnit.HOURS.between(
            reservation.getCheckInDate(), 
            reservation.getCheckOutDate()
        );
        long days = totalHours / 24;
        long remainingHours = totalHours % 24;
        
        // Calcular precio por cada habitación
        for (ReservationRoom reservationRoom : reservation.getReservationRooms()) {
            Room room = reservationRoom.getRoom();
            RoomType roomType = room.getRoomType();
            BigDecimal pricePerDay = roomType.getPricePerDay();
            BigDecimal pricePerNight = roomType.getPricePerNight();
            
            BigDecimal roomTotal;
            
            // Si la estancia es menor a un día completo (24 horas)
            if (days == 0) {
                // Se cobra por horas según tarifa diurna (usando precio por día)
                if (remainingHours <= 8) {
                    // Si es menos de 8 horas, cobrar proporcionalmente
                    roomTotal = pricePerDay
                        .multiply(BigDecimal.valueOf(remainingHours))
                        .divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
                } else {
                    // Si es más de 8 horas pero menos de 24, cobrar noche completa
                    roomTotal = pricePerNight;
                }
            } else {
                // Para estancias de más de un día
                roomTotal = pricePerNight.multiply(BigDecimal.valueOf(days));
                
                // Agregar horas adicionales si hay
                if (remainingHours > 0) {
                    if (remainingHours <= 6) {
                        // Hasta 6 horas adicionales, cobro proporcional
                        BigDecimal additionalCharge = pricePerDay
                            .multiply(BigDecimal.valueOf(remainingHours))
                            .divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
                        roomTotal = roomTotal.add(additionalCharge);
                    } else {
                        // Más de 6 horas adicionales, cobrar día/noche completa
                        roomTotal = roomTotal.add(pricePerNight);
                    }
                }
            }
            
            subtotal = subtotal.add(roomTotal);
        }
        
        // Calcular impuesto (ejemplo: 16%)
        BigDecimal taxRate = new BigDecimal("0.16");
        BigDecimal tax = subtotal.multiply(taxRate);
        BigDecimal total = subtotal.add(tax);
        
        invoice.setSubtotal(subtotal);
        invoice.setTax(tax);
        invoice.setTotal(total);
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        invoice.setIssueDate(LocalDateTime.now());
        
        return invoice;
    }
    
    /**
     * Obtiene una reservación por su ID
     */
    public RequestReservation getReservationById(int id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
        return convertToDTO(reservation);
    }
    
    /**
     * Convierte una entidad Reservation a su DTO
     */
    private RequestReservation convertToDTO(Reservation reservation) {
        RequestReservation dto = new RequestReservation();
        dto.setId(reservation.getId());
        dto.setClientName(reservation.getClientName());
        dto.setClientEmail(reservation.getClientEmail());
        dto.setClientPhone(reservation.getClientPhone());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setStatus(reservation.getStatus().toString());
        dto.setNotes(reservation.getNotes());
        dto.setIdEmployee(reservation.getEmployee().getId());
        dto.setEmployeeName(reservation.getEmployee().getFirstName() + " " + reservation.getEmployee().getLastName());
        
        // Convertir habitaciones
        List<RequestReservationRoom> roomDTOs = new ArrayList<>();
        for (ReservationRoom room : reservation.getReservationRooms()) {
            RequestReservationRoom roomDTO = new RequestReservationRoom();
            roomDTO.setId(room.getId());
            roomDTO.setIdReservation(reservation.getId());
            roomDTO.setIdRoom(room.getRoom().getId());
            roomDTO.setRoomNumber(room.getRoom().getRoomNumber());
            roomDTO.setIdRoomType(room.getRoom().getRoomType().getId());
            roomDTO.setRoomTypeName(room.getRoom().getRoomType().getName());
            roomDTO.setPricePerDay(room.getRoom().getRoomType().getPricePerDay());
            roomDTO.setPricePerNight(room.getRoom().getRoomType().getPricePerNight());
            roomDTOs.add(roomDTO);
        }
        dto.setRooms(roomDTOs);
        
        // Convertir factura
        if (reservation.getInvoice() != null) {
            RequestInvoice invoiceDTO = new RequestInvoice();
            invoiceDTO.setId(reservation.getInvoice().getId());
            invoiceDTO.setIdReservation(reservation.getId());
            invoiceDTO.setSubtotal(reservation.getInvoice().getSubtotal());
            invoiceDTO.setTax(reservation.getInvoice().getTax());
            invoiceDTO.setTotal(reservation.getInvoice().getTotal());
            invoiceDTO.setPaymentStatus(reservation.getInvoice().getPaymentStatus().toString());
            invoiceDTO.setIssueDate(reservation.getInvoice().getIssueDate());
            dto.setInvoice(invoiceDTO);
        }
        
        return dto;
    }
    
    /**
     * Actualiza el estado de una reservación
     */
    public RequestReservation updateReservationStatus(int id, String status) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
            
        reservation.setStatus(ReservationStatus.valueOf(status));
        reservation = reservationRepository.save(reservation);
        
        return convertToDTO(reservation);
    }
    
    /**
     * Verifica disponibilidad de habitaciones para fechas dadas
     */
    public boolean checkRoomAvailability(int roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
        // Buscar reservaciones activas que se solapen con las fechas solicitadas
        List<ReservationRoom> reservedRooms = reservationRoomRepository.findByRoomIdAndDateRange(
            roomId, checkIn, checkOut);
            
        return reservedRooms.isEmpty();
    }
}
