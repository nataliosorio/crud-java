package com.sena.crud_basic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room", length = 10, nullable = false)
    private int id;

    @Column(name = "room_number", length = 10, nullable = false, unique = true)
    private String roomNumber;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // Available, Occupied, Maintenance

    // Relación con Hotel (Una habitación pertenece a un hotel)
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    // Relación con TypeRoom (Una habitación pertenece a un tipo de habitación)
    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private typeRoom roomType;
}
