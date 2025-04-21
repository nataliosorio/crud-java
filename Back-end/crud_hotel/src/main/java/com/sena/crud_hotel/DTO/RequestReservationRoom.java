package com.sena.crud_hotel.DTO;

import java.math.BigDecimal;

public class RequestReservationRoom {

    private int id;
    private int roomId;
    private String roomNumber;

    private BigDecimal appliedPrice; // Precio calculado (por día o noche)


    public RequestReservationRoom() {
    }

    public RequestReservationRoom(int id, int roomId, String roomNumber, BigDecimal appliedPrice) {
        this.id = id;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.appliedPrice = appliedPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setAppliedPrice(BigDecimal appliedPrice) {
        this.appliedPrice = appliedPrice;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public BigDecimal getAppliedPrice() {
        return appliedPrice;
    }

    
}
