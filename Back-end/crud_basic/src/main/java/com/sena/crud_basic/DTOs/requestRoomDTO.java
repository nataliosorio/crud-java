package com.sena.crud_basic.DTOs;

// import com.sena.crud_basic.model.Hotel;
// import com.sena.crud_basic.model.typeRoom;

public class requestRoomDTO {
    private String roomNumber;
    private String status;
     private int hotel;
     private int roomType;

    public requestRoomDTO(String roomNumber, String status, int hotel, int roomType) {
        this.roomNumber = roomNumber;
        this.status = status;
        this.hotel = hotel;
        this.roomType = roomType;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getStatus() {
        return status;
    }


    public int getHotel() {
        return hotel;
    }


    public int getRoomType() {
        return roomType;
    }
  
}
