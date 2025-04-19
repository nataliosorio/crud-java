package com.sena.crud_hotel.DTO;


public class requestEmployee {

    private int id;
    private String firstName;
    private String lastName;
    private int idDocumentType;
    private String documentTypeName;
    private String documentNumber;
    private String email;
    private String phone;
    private int idRole;
    private String rolName;
    private int idHotel;
    private String hotelName;

    public requestEmployee() {
    }

    public requestEmployee(int id, String firstName, String lastName, int idDocumentType, String documentTypeName,
            String documentNumber, String email, String phone, int idRole, String rolName, int idHotel,
            String hotelName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idDocumentType = idDocumentType;
        this.documentTypeName = documentTypeName;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.idRole = idRole;
        this.rolName = rolName;
        this.idHotel = idHotel;
        this.hotelName = hotelName;
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

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRolName() {
        return rolName;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public String getHotelName() {
        return hotelName;
    }

    





    

}
