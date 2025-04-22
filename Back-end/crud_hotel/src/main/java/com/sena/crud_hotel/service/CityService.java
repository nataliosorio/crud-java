package com.sena.crud_hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestCity;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.interfaces.ICity;
import com.sena.crud_hotel.model.City;

@Service
public class CityService {

    @Autowired
    private ICity cityData; 

    public List<City> finAllTypeRooms(){
        return cityData.findAll();
    }

    public Optional<City> finByIdTypeRooms(int id){
        return cityData.findById(id);
    }

    public void save(requestCity typeRoom) {
        cityData.save(converRegisterToTypeRoom(typeRoom));
    }

    public City converRegisterToTypeRoom(requestCity typeRoom) {
        return new City(
                0,
                typeRoom.getName()
        );
    }

    public requestCity update(int id, requestCity typeRoomUpdate) {
        City room = cityData.findById(id)
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con ID: " + id));

        room.setName(typeRoomUpdate.getName());

        City updatedRoom = cityData.save(room);

        requestCity updatedDto = new requestCity();
        updatedDto.setId(updatedRoom.getId());
        updatedDto.setName(updatedRoom.getName());

        return updatedDto;

    }

   

    public responseDTO delete(int id) {
        var typeRoom = finByIdTypeRooms(id);
        responseDTO response=new responseDTO();
        if (typeRoom.isPresent()) {
            cityData.delete(typeRoom.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Se elimino correctamente");
            return response;
        }else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro no existe");
            return response;
        }
    } 
}
