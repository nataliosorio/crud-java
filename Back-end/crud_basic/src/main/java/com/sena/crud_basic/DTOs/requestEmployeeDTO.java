package com.sena.crud_basic.DTOs;



public class requestEmployeeDTO {

    private String firstName;
    private String lastName;
    private int documentType;
    private String documentNumber;
    private String phone;
    private String email;
    private int role;
    private int hotel;

    public requestEmployeeDTO(String firstName, String lastName, int documentType, String documentNumber, String phone,
            String email, int role, int hotel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.hotel = hotel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public int getHotel() {
        return hotel;
    }

    

    

}
