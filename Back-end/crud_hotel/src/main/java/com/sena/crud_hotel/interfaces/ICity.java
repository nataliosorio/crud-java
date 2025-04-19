package com.sena.crud_hotel.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_hotel.model.City;

public interface ICity extends JpaRepository<City, Integer>{

    
}
