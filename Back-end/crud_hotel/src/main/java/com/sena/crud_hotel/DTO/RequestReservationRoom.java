package com.sena.crud_hotel.DTO;

import java.math.BigDecimal;

public class RequestReservationRoom {

    private int id;
    private int idReservation;
    private int idRoom;
    private String roomNumber;
    private int idRoomType;
    private String roomTypeName;
    private BigDecimal pricePerNight;
    private BigDecimal pricePerDay;

    public RequestReservationRoom() {
    }

    public RequestReservationRoom(int id, int idReservation, int idRoom, String roomNumber, 
                             int idRoomType, String roomTypeName, 
                             BigDecimal pricePerNight, BigDecimal pricePerDay) {
        this.id = id;
        this.idReservation = idReservation;
        this.idRoom = idRoom;
        this.roomNumber = roomNumber;
        this.idRoomType = idRoomType;
        this.roomTypeName = roomTypeName;
        this.pricePerNight = pricePerNight;
        this.pricePerDay = pricePerDay;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(int idRoomType) {
        this.idRoomType = idRoomType;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
