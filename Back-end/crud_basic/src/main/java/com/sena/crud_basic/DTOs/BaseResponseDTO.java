package com.sena.crud_basic.DTOs;

public class BaseResponseDTO {
    private String message;

    public BaseResponseDTO(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
