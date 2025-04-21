package com.sena.crud_hotel.DTO;

import java.util.ArrayList;
import java.util.List;

public class CreateReservationRequest {

    private int customerId;
    private int employeeId;
    private int numberday;
    private int numberNight;

    private String notes;
    private List<Integer> roomIds = new ArrayList<>(); // Solo los IDs, luego tú cargas los Room y calculas precios

    public CreateReservationRequest() {
    }

    public CreateReservationRequest(int customerId, int employeeId, int numberday, int numberNight, String notes,
            List<Integer> roomIds) {
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.numberday = numberday;
        this.numberNight = numberNight;
        this.notes = notes;
        this.roomIds = roomIds;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

   

    public void setNumberday(int numberday) {
        this.numberday = numberday;
    }

    public void setNumberNight(int numberNight) {
        this.numberNight = numberNight;
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

  

    public int getNumberday() {
        return numberday;
    }

    public int getNumberNight() {
        return numberNight;
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
