package com.sena.crud_hotel.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateReservationRequest {

    private int customerId;
    private int employeeId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    private String notes;
    private List<Integer> roomIds = new ArrayList<>(); // Solo los IDs, luego tú cargas los Room y calculas precios

    public CreateReservationRequest() {
    }

    public CreateReservationRequest(int customerId, int employeeId, LocalDateTime checkInDate, LocalDateTime checkOutDate, String notes,
            List<Integer> roomIds) {
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.notes = notes;
        this.roomIds = roomIds;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRoomIds(List<Integer> roomIds) {
        this.roomIds = roomIds;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public String getNotes() {
        return notes;
    }

    public List<Integer> getRoomIds() {
        return roomIds;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    

   

    
}
