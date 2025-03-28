package com.sena.crud_basic.DTOs;



public class requestReservationRoomDTO {
    // private int id;
    private int reservation;
    private int room;
    
    public requestReservationRoomDTO(int reservation, int room) {
        // this.id = id;
        this.reservation = reservation;
        this.room = room;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    // public int getId() {
    //     return id;
    // }

    public int getReservation() {
        return reservation;
    }

    public int getRoom() {
        return room;
    }

    


}
