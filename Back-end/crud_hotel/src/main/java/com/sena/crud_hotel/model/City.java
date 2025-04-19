package com.sena.crud_hotel.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city", length = 10, nullable = false)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    // Relación con Hotel (Una ciudad puede tener muchos hoteles)
    @OneToMany(mappedBy = "city")
    private List<Hotel> hotels;

    public City() {
    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    } 
    

}
