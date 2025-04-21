package com.sena.crud_hotel.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    // @Column(name = "check_in_date", nullable = false)
    // private LocalDateTime checkInDate;

    @Column(name = "numberday")
    private int numberday;

    @Column(name = "numberNight")
    private int numberNight;

    
    // @Column(name = "check_out_date", nullable = false)
    // private LocalDateTime checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private EnumReservation status;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationRoom> reservationRooms = new ArrayList<>();

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Invoice invoice;

    public Reservation() {
    }

    public Reservation(int id, Customer customer, Employee employee, int numberday, int numberNight,
            EnumReservation status, String notes, LocalDateTime createdAt,
            List<ReservationRoom> reservationRooms, Invoice invoice) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.numberday = numberday;
        this.numberNight = numberNight;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
        this.reservationRooms = reservationRooms;
        this.invoice = invoice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

   

    public void setNumberday(int numberday) {
        this.numberday = numberday;
    }

    public void setNumberNight(int numberNight) {
        this.numberNight = numberNight;
    }

    public void setStatus(EnumReservation status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setReservationRooms(List<ReservationRoom> reservationRooms) {
        this.reservationRooms = reservationRooms;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberday() {
        return numberday;
    }

    public int getNumberNight() {
        return numberNight;
    }

    public EnumReservation getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ReservationRoom> getReservationRooms() {
        return reservationRooms;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    

    

  
}
