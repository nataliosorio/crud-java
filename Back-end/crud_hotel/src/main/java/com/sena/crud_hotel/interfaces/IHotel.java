package com.sena.crud_hotel.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_hotel.DTO.requestHotel;
import com.sena.crud_hotel.model.Hotel;

public interface IHotel extends JpaRepository<Hotel, Integer>{



@Query("SELECT new com.sena.crud_hotel.DTO.requestHotel(" +
       "h.id, h.name, h.address, h.phone, h.email, c.id, c.name) " +
       "FROM Hotel h " +
       "JOIN h.city c")
List<requestHotel> findAllHotelsWithCity();
}
