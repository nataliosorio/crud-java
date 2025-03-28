package com.sena.crud_basic.DTOs;


public class requestHotelDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
    private int stars;
    private int city;

    public requestHotelDTO(String name, String address, String phone, String email, int stars, int city) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.stars = stars;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getStars() {
        return stars;
    }

    public int getCity() {
        return city;
    }


}
