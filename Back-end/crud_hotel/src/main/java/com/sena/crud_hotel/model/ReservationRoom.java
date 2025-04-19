package com.sena.crud_hotel.model;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation_room")
public class ReservationRoom {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation_room")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private Room room;

    @Column(name = "applied_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal appliedPrice;

    public ReservationRoom() {
    }

    public ReservationRoom(int id, Reservation reservation, Room room, BigDecimal appliedPrice) {
        this.id = id;
        this.reservation = reservation;
        this.room = room;
        this.appliedPrice = appliedPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setAppliedPrice(BigDecimal appliedPrice) {
        this.appliedPrice = appliedPrice;
    }

    public int getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Room getRoom() {
        return room;
    }

    public BigDecimal getAppliedPrice() {
        return appliedPrice;
    }

    
    
}
