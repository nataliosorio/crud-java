package com.sena.crud_hotel.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_hotel.model.Hotel;

public interface IHotel extends JpaRepository<Hotel, Integer>{

}
