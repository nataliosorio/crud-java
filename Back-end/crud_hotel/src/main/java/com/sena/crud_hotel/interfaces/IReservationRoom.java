package com.sena.crud_hotel.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_hotel.model.ReservationRoom;

public interface IReservationRoom extends JpaRepository<ReservationRoom,Integer>{

    // List<ReservationRoom> findByReservationId(int reservationId);
    
    // // Buscar habitaciones reservadas en un rango de fechas
    // @Query("SELECT rr FROM ReservationRoom rr JOIN rr.reservation r WHERE rr.room.id = :roomId " +
    //        "AND r.status NOT IN ('CANCELLED', 'CHECKED_OUT') " +
    //        "AND r.checkInDate <= :checkOut AND r.checkOutDate >= :checkIn")
    // List<ReservationRoom> findByRoomIdAndDateRange(
    //     @Param("roomId") int roomId, 
    //     @Param("checkIn") LocalDateTime checkIn, 
    //     @Param("checkOut") LocalDateTime checkOut
    // );
    
    // // Buscar todas las habitaciones ocupadas para una fecha específica
    // @Query("SELECT rr FROM ReservationRoom rr JOIN rr.reservation r WHERE " +
    //        "r.status NOT IN ('CANCELLED', 'CHECKED_OUT') " +
    //        "AND r.checkInDate <= :date AND r.checkOutDate >= :date")
    // List<ReservationRoom> findOccupiedRoomsOnDate(@Param("date") LocalDateTime date);

    // Para validar solapamientos de fechas
@Query("SELECT rr FROM ReservationRoom rr " +
"WHERE rr.room.id = :roomId " +
"AND rr.reservation.checkInDate < :checkOut " +
"AND rr.reservation.checkOutDate > :checkIn")
List<ReservationRoom> findConflictingReservations(@Param("roomId") int roomId,
                                           @Param("checkIn") LocalDateTime checkIn,
                                           @Param("checkOut") LocalDateTime checkOut);

List<ReservationRoom> findByReservationId(int reservationId);
}
