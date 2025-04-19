package com.sena.crud_hotel.DTO;

public class requestCustomer {

    private int id;
    private String firstName;
    private String lastName;
    private int idDocumentType;
    private String documentName;
    private String documentNumber;
    private String phone;
    private String email;
    public requestCustomer() {
    }
    public requestCustomer(int id, String firstName, String lastName, int idDocumentType, String documentName,
            String documentNumber, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idDocumentType = idDocumentType;
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
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
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getIdDocumentType() {
        return idDocumentType;
    }
    public String getDocumentName() {
        return documentName;
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
    



}
