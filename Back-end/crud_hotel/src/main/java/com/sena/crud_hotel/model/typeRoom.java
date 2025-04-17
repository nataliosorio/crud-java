package com.sena.crud_hotel.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;

@Entity(name = "typeRoom")
public class typeRoom {
    //@Id =  indica que es la primary key
    @Id
    //Indica que el valor sea generado e incremental 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    //@column indica que es una columna en la tabla de la base de datos
    @Column(name = "id_type_room", length = 10, nullable = false)
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price_day", precision = 10, scale = 2, nullable = false)
    private BigDecimal priceDay;

    @Column(name = "price_night", precision = 10, scale = 2, nullable = false)
    private BigDecimal priceNight;

    // Relación con Room (una categoría de habitación puede estar en muchas habitaciones)
    // @OneToMany(mappedBy = "roomType")
    // private List<Room> rooms;

    public typeRoom() {
    }

    public typeRoom(int id, String name, BigDecimal priceDay, BigDecimal priceNight) {
        this.id = id;
        this.name = name;
        this.priceDay = priceDay;
        this.priceNight = priceNight;
        // this.rooms = rooms;
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

    // public void setRooms(List<Room> rooms) {
    //     this.rooms = rooms;
    // }

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

    // public List<Room> getRooms() {
    //     return rooms;
    // }
    
}
