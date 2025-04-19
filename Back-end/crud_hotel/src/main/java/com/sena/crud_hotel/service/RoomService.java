package com.sena.crud_hotel.service;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestRoom;
import com.sena.crud_hotel.interfaces.IHotel;
import com.sena.crud_hotel.interfaces.IRoom;
import com.sena.crud_hotel.interfaces.ITypeRoom;
import com.sena.crud_hotel.model.Hotel;
import com.sena.crud_hotel.model.Room;
import com.sena.crud_hotel.model.typeRoom;

@Service
public class RoomService {
    
    @Autowired
    private IRoom roomRepository;

    @Autowired
    private IHotel hotelRepository;

    @Autowired
    private ITypeRoom typeRoomRepository;

    public List<requestRoom> getAllRoomsWithDetails() {
        return roomRepository.findAllRoomsWithDetails();
    }

    public requestRoom getRoomById(int id) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));
    
        requestRoom dto = new requestRoom();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setName(room.getName());
        dto.setStatus(room.getStatus());
    
        dto.setidHotel(room.getHotel().getId());
        dto.setHotelName(room.getHotel().getName());
    
        dto.setidTypeRoom(room.getRoomType().getId());
        dto.setRoomTypeName(room.getRoomType().getName());
    
        return dto;
    }
    
    public requestRoom createRoom(requestRoom roomDTO) {
        if (roomDTO.getidHotel() <= 0 || roomDTO.getidTypeRoom() <= 0) {
            throw new RuntimeException("ID del hotel o del tipo de habitación no válido.");
        }
    
        Hotel hotel = hotelRepository.findById(roomDTO.getidHotel())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + roomDTO.getidHotel()));
    
        typeRoom typeRoom = typeRoomRepository.findById(roomDTO.getidTypeRoom())
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado con ID: " + roomDTO.getidTypeRoom()));
    
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setName(roomDTO.getName());
        room.setStatus(roomDTO.getStatus());
        room.setHotel(hotel);
        room.setRoomType(typeRoom);
    
        Room savedRoom = roomRepository.save(room);
    
        return convertToDTO(savedRoom);
    }

    public requestRoom convertToDTO(Room room) {
        requestRoom dto = new requestRoom();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setName(room.getName());
        dto.setStatus(room.getStatus());
    
        dto.setidHotel(room.getHotel().getId());
        dto.setHotelName(room.getHotel().getName());
    
        dto.setidTypeRoom(room.getRoomType().getId());
        dto.setRoomTypeName(room.getRoomType().getName());
    
        return dto;
    }
    

    public requestRoom updateRoom(int id, requestRoom dto) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));
    
        room.setRoomNumber(dto.getRoomNumber());
        room.setName(dto.getName());
        room.setStatus(dto.getStatus());
    
        Hotel hotel = hotelRepository.findById(dto.getidHotel())
            .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + dto.getidHotel()));
        room.setHotel(hotel);
    
        typeRoom typeRoom = typeRoomRepository.findById(dto.getidTypeRoom())
            .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado con ID: " + dto.getidTypeRoom()));
        room.setRoomType(typeRoom);
    
        Room updatedRoom = roomRepository.save(room);
        
        requestRoom updatedDto = new requestRoom();
        updatedDto.setId(updatedRoom.getId());
        updatedDto.setRoomNumber(updatedRoom.getRoomNumber());
        updatedDto.setName(updatedRoom.getName());
        updatedDto.setStatus(updatedRoom.getStatus());
        updatedDto.setidHotel(hotel.getId());
        updatedDto.setHotelName(hotel.getName());
        updatedDto.setidTypeRoom(typeRoom.getId());
        updatedDto.setRoomTypeName(typeRoom.getName());
    
        return updatedDto;
    }
    

    // Eliminar una habitación
    
    public void deleteRoom(int id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Habitación no encontrada con ID: " + id);
        }
        roomRepository.deleteById(id);
    }

}
