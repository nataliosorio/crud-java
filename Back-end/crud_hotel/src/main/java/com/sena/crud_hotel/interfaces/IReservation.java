package com.sena.crud_hotel.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_hotel.model.Reservation;

public interface IReservation extends JpaRepository<Reservation, Integer>{

    @Query("SELECT r FROM Reservation r " +
    "JOIN FETCH r.customer " +
    "JOIN FETCH r.employee " +
    "LEFT JOIN FETCH r.invoice " +
    "LEFT JOIN FETCH r.reservationRooms rr " +
    "LEFT JOIN FETCH rr.room")
List<Reservation> findAllWithDetails();

@Query("SELECT r FROM Reservation r " +
    "JOIN FETCH r.customer " +
    "JOIN FETCH r.employee " +
    "LEFT JOIN FETCH r.invoice " +
    "LEFT JOIN FETCH r.reservationRooms rr " +
    "LEFT JOIN FETCH rr.room " +
    "WHERE r.id = :id")
Optional<Reservation> findByIdWithDetails(@Param("id") Integer id);
}
