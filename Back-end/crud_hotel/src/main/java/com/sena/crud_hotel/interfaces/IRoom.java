package com.sena.crud_hotel.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_hotel.DTO.requestRoom;
import com.sena.crud_hotel.model.Room;

public interface IRoom extends JpaRepository<Room, Integer>{


@Query("SELECT new com.sena.crud_hotel.DTO.requestRoom(" +
       "r.id, r.roomNumber, r.name, r.status, h.id, h.name, t.id, t.name) " +
       "FROM Room r " +
       "JOIN r.hotel h " +
       "JOIN r.roomType t")
List<requestRoom> findAllRoomsWithDetails();


}
