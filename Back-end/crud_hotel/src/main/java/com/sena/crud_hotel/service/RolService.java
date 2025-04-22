package com.sena.crud_hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestRol;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.interfaces.IRol;
import com.sena.crud_hotel.model.Role;

@Service
public class RolService {

    @Autowired
    private IRol rolData; 

    public List<Role> finAllTypeRooms(){
        return rolData.findAll();
    }

    public Optional<Role> finByIdTypeRooms(int id){
        return rolData.findById(id);
    }

    public void save(requestRol typeRoom) {
        rolData.save(converRegisterToTypeRoom(typeRoom));
    }

    public Role converRegisterToTypeRoom(requestRol typeRoom) {
        return new Role(
                0,
                typeRoom.getName()
        );
    }

    // public void update(requestRol typeRoomUpdate) {
    //     var TypeRoom = finByIdTypeRooms(typeRoomUpdate.getId());
    //     if (TypeRoom.isPresent()) {
    //         TypeRoom.get().setName(typeRoomUpdate.getName());
    //         rolData.save(TypeRoom.get());
    //     }
    // }


    public requestRol update(int id, requestRol typeRoomUpdate) {
        Role room = rolData.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrada con ID: " + id));

        room.setName(typeRoomUpdate.getName());

        Role updatedRoom = rolData.save(room);

        requestRol updatedDto = new requestRol();
        updatedDto.setId(updatedRoom.getId());
        updatedDto.setName(updatedRoom.getName());

        return updatedDto;

    }

    public responseDTO delete(int id) {
        var typeRoom = finByIdTypeRooms(id);
        responseDTO response=new responseDTO();
        if (typeRoom.isPresent()) {
            rolData.delete(typeRoom.get());
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
