package com.sena.crud_basic.DTOs;

import com.sena.crud_basic.model.Document_type;

public class requestCustomerDTO {
    private String firstName;
    private String lastName;
    private Document_type documentType;
    private String documentNumber;
    private String phone;
    private String email;
    
    public requestCustomerDTO(String firstName, String lastName, Document_type documentType, String documentNumber,
            String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDocumentType(Document_type documentType) {
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
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Document_type getDocumentType() {
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

    

}
