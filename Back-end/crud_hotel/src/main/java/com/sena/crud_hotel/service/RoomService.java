package com.sena.crud_hotel.service;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_hotel.DTO.requestRoom;
import com.sena.crud_hotel.interfaces.IHotel;
import com.sena.crud_hotel.interfaces.IRoom;
import com.sena.crud_hotel.interfaces.ITypeRoom;
import com.sena.crud_hotel.model.Hotel;
import com.sena.crud_hotel.model.Room;
import com.sena.crud_hotel.model.typeRoom;

public class RoomService {
    
    @Autowired
    private IRoom roomRepository;

    @Autowired
    private IHotel hotelRepository;

    @Autowired
    private ITypeRoom typeRoomRepository;

    // Obtener todas las habitaciones con detalles (JOIN a hotel y typeRoom)
    public List<requestRoom> getAllRoomsWithDetails() {
        return roomRepository.findAllRoomsWithDetails();
    }

    // Obtener una habitación por ID
    public requestRoom getRoomById(int id) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));

        requestRoom dto = new requestRoom();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setName(room.getName());
        dto.setStatus(room.getStatus());
        dto.setId(room.getHotel().getId());
        dto.setId(room.getRoomType().getId());
        return dto;
    }

    // Crear una nueva habitación
    public Room createRoom(requestRoom dto) {
        Hotel hotel = hotelRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + dto.getId()));

        typeRoom typeRoom = typeRoomRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado con ID: " + dto.getId()));

        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setName(dto.getName());
        room.setStatus(dto.getStatus());
        room.setHotel(hotel);
        room.setRoomType(typeRoom);

        return roomRepository.save(room);
    }

    // Actualizar una habitación existente
    public Room updateRoom(int id, requestRoom dto) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));

        Hotel hotel = hotelRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + dto.getId()));

        typeRoom typeRoom = typeRoomRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado con ID: " + dto.getId()));

        room.setRoomNumber(dto.getRoomNumber());
        room.setName(dto.getName());
        room.setStatus(dto.getStatus());
        room.setHotel(hotel);
        room.setRoomType(typeRoom);

        return roomRepository.save(room);
    }

    // Eliminar una habitación
    public void deleteRoom(int id) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));

        roomRepository.delete(room);
    }


    


}
