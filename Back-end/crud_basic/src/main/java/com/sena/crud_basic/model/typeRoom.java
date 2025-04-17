package com.sena.crud_basic.model;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
//con esta anotación bean se indica que la clase es una entidad

@Entity(name = "typeRoom")
@Getter
@Setter
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
    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
}