package com.sena.crud_hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestHotel;
import com.sena.crud_hotel.interfaces.ICity;
import com.sena.crud_hotel.interfaces.IHotel;
import com.sena.crud_hotel.model.City;
import com.sena.crud_hotel.model.Hotel;

@Service
public class HotelService {

     @Autowired
    private IHotel hotelRepository;

    @Autowired
    private ICity cityData;

    public List<requestHotel> getAllRoomsWithDetails() {
        return hotelRepository.findAllHotelsWithCity();
    }

    public requestHotel getHotelById(int id) {
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
    
        requestHotel dto = new requestHotel();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setAddress(hotel.getAddress());
        dto.setPhone(hotel.getPhone());
        dto.setEmail(hotel.getEmail());
        dto.setStars(hotel.getStars());
        dto.setidCity(hotel.getId());
        dto.setCityName(hotel.getName());
    
        return dto;
    }


     public requestHotel createHotel(requestHotel roomDTO) {
        if (roomDTO.getidCity() <= 0) {
            throw new RuntimeException("ID de la ciudad no válida.");
        }
    
        
        City city = cityData.findById(roomDTO.getidCity())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + roomDTO.getidCity()));
    
        Hotel hotel = new Hotel();
        hotel.setName(roomDTO.getName());
        hotel.setAddress(roomDTO.getAddress());
        hotel.setPhone(roomDTO.getPhone());
        hotel.setEmail(roomDTO.getEmail());
        hotel.setStars(roomDTO.getStarts());
        hotel.setCity(city);

 
        Hotel savedHotel = hotelRepository.save(hotel);
    
        return convertToDTO(savedHotel);
    }

    public requestHotel convertToDTO(Hotel room) {
        requestHotel dto = new requestHotel();

        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setAddress(room.getAddress());
        dto.setPhone(room.getPhone());
        dto.setEmail(room.getEmail());
        dto.setStars(room.getStars());
    
        dto.setidCity(room.getCity().getId());
        dto.setCityName(room.getCity().getName());
    
        return dto;
    }

    public requestHotel updateRoom(int id, requestHotel dto) {
        Hotel room = hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
    
        room.setName(dto.getName());
        room.setAddress(dto.getAddress());
        room.setPhone(dto.getPhone());
        room.setEmail(dto.getEmail());
        room.setStars(dto.getStarts());

    
    
        City city = cityData.findById(dto.getidCity())
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrado con ID: " + dto.getidCity()));
        room.setCity(city);
    
        Hotel updatedRoom = hotelRepository.save(room);
        
        requestHotel updatedDto = new requestHotel();
        updatedDto.setId(updatedRoom.getId());
        updatedDto.setName(updatedRoom.getName());
        updatedDto.setAddress(updatedRoom.getAddress());
        updatedDto.setPhone(updatedRoom.getPhone());
        updatedDto.setEmail(updatedRoom.getEmail());
        updatedDto.setStars(updatedRoom.getStars());

        updatedDto.setidCity(city.getId());
        updatedDto.setCityName(city.getName());
        
        return updatedDto;
    }


    // Eliminar una habitación
    

    public void deleteRoom(int id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel no encontrado con ID: " + id);
        }
        hotelRepository.deleteById(id);
    }

    

}
