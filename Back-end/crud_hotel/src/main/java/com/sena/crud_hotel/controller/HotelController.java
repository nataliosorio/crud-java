package com.sena.crud_hotel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.DTO.requestHotel;
import com.sena.crud_hotel.service.HotelService;

@RestController
@RequestMapping("api/v1/Hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

  // Obtener todas las habitaciones con detalles
    @GetMapping("/")
    public List<requestHotel> getAllRooms() {
        return hotelService.getAllRoomsWithDetails();
    }

    // Obtener una habitación por ID
    @GetMapping("/{id}")
    public requestHotel getRoomById(@PathVariable int id) {
        return hotelService.getHotelById(id);
    }


    @PostMapping("/")
    public ResponseEntity<requestHotel> createRoom(@RequestBody requestHotel roomDTO) {
        requestHotel createdRoom = hotelService.createHotel(roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    // Actualizar una habitación existente
    
    @PutMapping("/{id}")
    public requestHotel updateRoom(@PathVariable int id, @RequestBody requestHotel dto) {
        return hotelService.updateRoom(id, dto);
    }

    // Eliminar una habitación
 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int id) {
        hotelService.deleteRoom(id);
        return ResponseEntity.ok("Hotel eliminado correctamente");
    }
}
