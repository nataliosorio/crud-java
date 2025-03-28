package com.sena.crud_basic.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Getter
@Setter
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
}