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

import com.sena.crud_hotel.DTO.requestRoom;
import com.sena.crud_hotel.service.RoomService;

@RestController
@RequestMapping("api/v1/Room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Obtener todas las habitaciones con detalles
    @GetMapping("/")
    public List<requestRoom> getAllRooms() {
        return roomService.getAllRoomsWithDetails();
    }

    // Obtener una habitación por ID
    @GetMapping("/{id}")
    public requestRoom getRoomById(@PathVariable int id) {
        return roomService.getRoomById(id);
    }


    @PostMapping("/")
    public ResponseEntity<requestRoom> createRoom(@RequestBody requestRoom roomDTO) {
        requestRoom createdRoom = roomService.createRoom(roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    // Actualizar una habitación existente
    
    @PutMapping("/{id}")
    public requestRoom updateRoom(@PathVariable int id, @RequestBody requestRoom dto) {
        return roomService.updateRoom(id, dto);
    }

    // Eliminar una habitación
 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Habitación eliminada correctamente");
    }
}
