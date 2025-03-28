package com.sena.crud_basic.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Hotel")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel", length = 10, nullable = false)
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "stars", nullable = false)
    private int stars;

    // Relación con City (Un hotel pertenece a una ciudad)
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    // Relación con Room (Un hotel tiene muchas habitaciones)
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}