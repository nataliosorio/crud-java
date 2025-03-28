package com.sena.crud_basic.DTOs;

import com.sena.crud_basic.model.Hotel;
import com.sena.crud_basic.model.typeRoom;

// import com.sena.crud_basic.model.Hotel;
// import com.sena.crud_basic.model.typeRoom;

public class requestRoomDTO {
    private String roomNumber;
    // private String status;
     private Hotel hotel;
     private typeRoom roomType;

    public requestRoomDTO() {
    }

    public requestRoomDTO(String roomNumber,  Hotel hotel, typeRoom roomType) {
        this.roomNumber = roomNumber;
        // this.status = status;
        this.hotel = hotel;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public typeRoom getRoomType() {
        return roomType;
    }

    public void setRoomType(typeRoom roomType) {
        this.roomType = roomType;
    }

   
    
  
}
