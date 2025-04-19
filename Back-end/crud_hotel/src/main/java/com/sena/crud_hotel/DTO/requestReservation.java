package com.sena.crud_hotel.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class requestReservation {

    private int id;
    private int idCliente;
    private String clientName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String status;
    private String notes;
    private int idEmployee;
    private String employeeName;
    private List<RequestReservationRoom> rooms;
    private RequestInvoice invoice;

    public requestReservation() {
    }

    public requestReservation(int id,int idCliente, String clientName, LocalDateTime checkInDate, LocalDateTime checkOutDate, String status,
                         String notes, int idEmployee, String employeeName) {
        this.id = id;
        this.idCliente = idCliente;
        this.clientName = clientName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.notes = notes;
        this.idEmployee = idEmployee;
        this.employeeName = employeeName;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

   
    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<RequestReservationRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<RequestReservationRoom> rooms) {
        this.rooms = rooms;
    }

    public RequestInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(RequestInvoice invoice) {
        this.invoice = invoice;
    }

   
    
}

   