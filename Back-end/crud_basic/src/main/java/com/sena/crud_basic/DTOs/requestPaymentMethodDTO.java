package com.sena.crud_basic.DTOs;

public class requestPaymentMethodDTO {
    private String name;

    public requestPaymentMethodDTO(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
