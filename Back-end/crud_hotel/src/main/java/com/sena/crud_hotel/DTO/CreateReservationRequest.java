package com.sena.crud_hotel.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CreateReservationRequest {

    private int idCliente;
    private String clientName;
    
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String notes;
    private int idEmployee;
    private List<Integer> roomIds;

    public CreateReservationRequest() {
    }

    public CreateReservationRequest(int idCliente, String clientName, 
                               LocalDateTime checkInDate, LocalDateTime checkOutDate, 
                               String notes, int idEmployee, List<Integer> roomIds) {
        this.idCliente = idCliente;
        this.clientName = clientName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.notes = notes;
        this.idEmployee = idEmployee;
        this.roomIds = roomIds;
    }

    // Getters y setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
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

    public List<Integer> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Integer> roomIds) {
        this.roomIds = roomIds;
    }

   

    
}
