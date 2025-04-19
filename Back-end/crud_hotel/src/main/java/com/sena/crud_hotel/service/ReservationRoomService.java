package com.sena.crud_hotel.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sena.crud_hotel.interfaces.IReservationRoom;
import com.sena.crud_hotel.interfaces.IRoom;
import com.sena.crud_hotel.model.ReservationRoom;
import com.sena.crud_hotel.model.Room;

import jakarta.transaction.Transactional;

@Service
public class ReservationRoomService {

    //  @Autowired
    // private IReservationRoom reservationRoomRepository;
    
    // @Autowired
    // private IReservation reservationRepository;
    
    // @Autowired
    // private IRoom roomRepository;
    
    // /**
    //  * CRUD: Obtener habitaciones de una reservación
    //  */
    // public List<RequestReservationRoom> getRoomsByReservationId(int reservationId) {
    //     List<ReservationRoom> rooms = reservationRoomRepository.findByReservationId(reservationId);
    //     return rooms.stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
    
    // /**
    //  * CRUD: Agregar habitación a una reservación existente
    //  */
    // public RequestReservationRoom addRoomToReservation(int reservationId, int roomId) {
    //     // Validar existencia de reservación y habitación
    //     Reservation reservation = reservationRepository.findById(reservationId)
    //         .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
            
    //     Room room = roomRepository.findById(roomId)
    //         .orElseThrow(() -> new EntityNotFoundException("Habitación no encontrada"));
        
    //     // Verificar que la habitación esté disponible para las fechas de la reservación
    //     if (!isRoomAvailable(roomId, reservation.getCheckInDate(), reservation.getCheckOutDate())) {
    //         throw new IllegalStateException("La habitación no está disponible para las fechas de la reservación");
    //     }
        
    //     // Crear relación
    //     ReservationRoom reservationRoom = new ReservationRoom();
    //     reservationRoom.setReservation(reservation);
    //     reservationRoom.setRoom(room);
        
    //     // Guardar
    //     reservationRoom = reservationRoomRepository.save(reservationRoom);
        
    //     // Recalcular factura
    //     recalculateInvoice(reservation);
        
    //     return convertToDTO(reservationRoom);
    // }
    
    // /**
    //  * CRUD: Eliminar habitación de una reservación
    //  */
    // public void removeRoomFromReservation(int reservationId, int roomId) {
    //     // Buscar la relación
    //     List<ReservationRoom> reservationRooms = reservationRoomRepository.findByReservationId(reservationId);
        
    //     Optional<ReservationRoom> roomToRemove = reservationRooms.stream()
    //         .filter(rr -> rr.getRoom().getId() == roomId)
    //         .findFirst();
        
    //     if (roomToRemove.isPresent()) {
    //         // Eliminar la relación
    //         reservationRoomRepository.delete(roomToRemove.get());
            
    //         // Recalcular factura
    //         Reservation reservation = reservationRepository.findById(reservationId)
    //             .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
    //         recalculateInvoice(reservation);
    //     } else {
    //         throw new EntityNotFoundException("Habitación no encontrada en la reservación");
    //     }
    // }
    
    // /**
    //  * Verificar disponibilidad de habitación para fechas específicas
    //  */
    // private boolean isRoomAvailable(int roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
    //     List<ReservationRoom> reservations = reservationRoomRepository.findByRoomIdAndDateRange(
    //         roomId, checkIn, checkOut);
    //     return reservations.isEmpty();
    // }
    
    // /**
    //  * Recalcular factura después de agregar/eliminar habitaciones
    //  */
    // private void recalculateInvoice(Reservation reservation) {
    //     // Aquí puedes llamar al servicio de facturación o implementar la lógica de cálculo
    //     // Este método debería actualizar la factura existente con los nuevos valores
    //     // basados en las habitaciones actuales de la reservación
    // }
    
    // /**
    //  * Listar habitaciones ocupadas para una fecha específica
    //  */
    // public List<RequestReservationRoom> getOccupiedRooms(LocalDateTime date) {
    //     List<ReservationRoom> occupiedRooms = reservationRoomRepository.findOccupiedRoomsOnDate(date);
    //     return occupiedRooms.stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
    
    // /**
    //  * Convertir una entidad ReservationRoom a su DTO
    //  */
    // private RequestReservationRoom convertToDTO(ReservationRoom reservationRoom) {
    //     RequestReservationRoom dto = new RequestReservationRoom();
    //     dto.setId(reservationRoom.getId());
    //     dto.setIdReservation(reservationRoom.getReservation().getId());
    //     dto.setIdRoom(reservationRoom.getRoom().getId());
    //     dto.setRoomNumber(reservationRoom.getRoom().getRoomNumber());
        
    //     // Datos del tipo de habitación
    //     typeRoom roomType = reservationRoom.getRoom().getRoomType();
    //     dto.setIdRoomType(roomType.getId());
    //     dto.setRoomTypeName(roomType.getName());
    //     dto.setPricePerDay(roomType.getPriceDay());
    //     dto.setPricePerNight(roomType.getPriceNight());
        
    //     return dto;
    // }

     private final IReservationRoom reservationRoomRepository;
    private final IRoom roomRepository;

    public ReservationRoomService(IReservationRoom reservationRoomRepository, IRoom roomRepository) {
        this.reservationRoomRepository = reservationRoomRepository;
        this.roomRepository = roomRepository;
    }

    // 1. Validar si una habitación está disponible en un rango de fechas
    public boolean isRoomAvailable(int roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
        List<ReservationRoom> conflicts = reservationRoomRepository.findConflictingReservations(roomId, checkIn, checkOut);
        return conflicts.isEmpty();
    }

    // 2. Obtener habitaciones por reserva
    public List<ReservationRoom> getRoomsByReservation(int reservationId) {
        return reservationRoomRepository.findByReservationId(reservationId);
    }

    // 3. Editar habitación asociada a una reserva
    @Transactional
    public void updateReservationRoom(int reservationRoomId, int newRoomId, BigDecimal newPrice) {
        ReservationRoom resRoom = reservationRoomRepository.findById(reservationRoomId)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));

        Room newRoom = roomRepository.findById(newRoomId)
                .orElseThrow(() -> new RuntimeException("Nueva habitación no encontrada"));

        resRoom.setRoom(newRoom);
        resRoom.setAppliedPrice(newPrice);
        reservationRoomRepository.save(resRoom);
    }

    // 4. Eliminar habitación de una reserva
    public void deleteReservationRoom(int reservationRoomId) {
        reservationRoomRepository.deleteById(reservationRoomId);
    }
}
