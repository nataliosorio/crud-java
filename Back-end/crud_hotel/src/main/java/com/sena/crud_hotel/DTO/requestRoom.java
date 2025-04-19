package com.sena.crud_hotel.DTO;

public class requestRoom {

    private int id;
    private String roomNumber;
    private String name;
    private String status;
    private int idHotel;
    private String hotelName;
    private int idTypeRoom;
    private String roomTypeName;

    public requestRoom() {
    }

    public requestRoom(int id, String roomNumber, String name, String status, int idHotel, String hotelName,int idTypeRoom, String roomTypeName) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.name = name;
        this.status = status;
        this.idHotel = idHotel;
        this.hotelName = hotelName;
        this.idTypeRoom = idTypeRoom;
        this.roomTypeName = roomTypeName;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getidHotel() {
        return idHotel;
    }

    public void setidTypeRoom(int idTypeRoom) {
        this.idTypeRoom = idTypeRoom;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getidTypeRoom() {
        return idTypeRoom;
    }

    public void setidHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }


}
