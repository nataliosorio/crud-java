package com.sena.crud_hotel.DTO;

public class requestCity {
    private int id;
    private String name;

    public requestCity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public requestCity() {
    }

    

}
