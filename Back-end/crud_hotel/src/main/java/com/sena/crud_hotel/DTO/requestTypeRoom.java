package com.sena.crud_hotel.DTO;

import java.math.BigDecimal;

public class requestTypeRoom {
    private int id;
    private String name;
    private BigDecimal priceDay;
    private BigDecimal priceNight;
    
    public requestTypeRoom() {
    }

    public requestTypeRoom(int id, String name, BigDecimal priceDay, BigDecimal priceNight) {
        this.id = id;
        this.name = name;
        this.priceDay = priceDay;
        this.priceNight = priceNight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceDay(BigDecimal priceDay) {
        this.priceDay = priceDay;
    }

    public void setPriceNight(BigDecimal priceNight) {
        this.priceNight = priceNight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceDay() {
        return priceDay;
    }

    public BigDecimal getPriceNight() {
        return priceNight;
    }

    




}
