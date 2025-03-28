package com.sena.crud_basic.DTOs;

public class requestRoleDTO {
    // private Long id;
    private String name;

    public requestRoleDTO(String name) {
        // this.id = id;
        this.name = name;
    }
    // public void setId(Long id) {
    //     this.id = id;
    // }
    public void setName(String name) {
        this.name = name;
    }
    // public Long getId() {
    //     return id;
    // }
    public String getName() {
        return name;
    }

}
