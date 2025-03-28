package com.sena.crud_basic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservation_rooms")
@Getter
@Setter
public class ReservationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation_room", length = 10, nullable = false)
    private int id;

    // Relación con Reservation
    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    // Relación con Room
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}