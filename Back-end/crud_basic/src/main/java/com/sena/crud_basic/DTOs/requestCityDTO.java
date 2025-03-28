package com.sena.crud_basic.DTOs;

public class requestCityDTO {
    private String name;

    public requestCityDTO(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
