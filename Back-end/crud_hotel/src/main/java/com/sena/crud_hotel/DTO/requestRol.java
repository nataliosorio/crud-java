package com.sena.crud_hotel.DTO;

public class requestRol {

    private int id;
    private String name;
    
    public requestRol() {
    }

    public requestRol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    

}
