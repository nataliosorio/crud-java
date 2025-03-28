package com.sena.crud_basic.DTOs;

public class requestTypeRoomDTO {

    private String name;
    private String description;
    
    public requestTypeRoomDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    


}


