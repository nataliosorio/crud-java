package com.sena.crud_hotel.DTO;

public class requestHotel {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int stars;
    private int idCity;
    private String cityName;

    public requestHotel() {
    }

    public requestHotel(int id, String name, String address, String phone, String email, int stars, int idCity, String cityName) {
        this.id = id;
        this.name = name;
        this.address = address;
        // this.city = city;
        this.phone = phone;
        this.email = email;
        this.stars = stars;
        this.idCity = idCity;
        this.cityName = cityName;
    }


    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // public String getCity() {
    //     return city;
    // }

    // public void setCity(String city) {
    //     this.city = city;
    // }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getStarts() {
        return stars;
    }

    public void setStars(int starts){
        this.stars = starts;
    }

    public int getidCity() {
        return idCity;
    }

    public void setidCity(int idCity) {
        this.idCity = idCity;
    }


    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

   
}
