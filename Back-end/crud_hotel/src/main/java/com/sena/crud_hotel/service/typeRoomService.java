package com.sena.crud_hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestTypeRoom;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.interfaces.ITypeRoom;
import com.sena.crud_hotel.model.typeRoom;

@Service
public class typeRoomService {

    @Autowired
    private ITypeRoom typeRoomData; 

    public List<typeRoom> finAllTypeRooms(){
        return typeRoomData.findAll();
    }

    public Optional<typeRoom> finByIdTypeRooms(int id){
        return typeRoomData.findById(id);
    }

    public void save(requestTypeRoom typeRoom) {
        typeRoomData.save(converRegisterToTypeRoom(typeRoom));
    }

    public typeRoom converRegisterToTypeRoom(requestTypeRoom typeRoom) {
        return new typeRoom(
                0,
                typeRoom.getName(),
                typeRoom.getPriceDay(),
                typeRoom.getPriceNight()
        );
    }

    public void update(requestTypeRoom typeRoomUpdate) {
        var TypeRoom = finByIdTypeRooms(typeRoomUpdate.getId());
        if (TypeRoom.isPresent()) {
            TypeRoom.get().setName(typeRoomUpdate.getName());
            TypeRoom.get().setPriceDay(typeRoomUpdate.getPriceDay());
            TypeRoom.get().setPriceNight(typeRoomUpdate.getPriceNight());
            typeRoomData.save(TypeRoom.get());
        }
    }

    public responseDTO delete(int id) {
        var typeRoom = finByIdTypeRooms(id);
        responseDTO response=new responseDTO();
        if (typeRoom.isPresent()) {
            typeRoomData.delete(typeRoom.get());
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
