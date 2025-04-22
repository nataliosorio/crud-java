package com.sena.crud_hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.DTO.requestTypeRoom;
import com.sena.crud_hotel.DTO.responseDTO;
// import com.sena.crud_hotel.model.typeRoom;
import com.sena.crud_hotel.service.typeRoomService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v1/typeRoom")
public class typeRoomController {
    @Autowired
    private typeRoomService typeRoomService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTypeRoom() {
        var ListTypeRoom = typeRoomService.finAllTypeRooms();
        return new ResponseEntity<Object>(ListTypeRoom,HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTypeRoom(@PathVariable int id) {
        var ListTypeRoom = typeRoomService.finByIdTypeRooms(id);
        return new ResponseEntity<>(ListTypeRoom,HttpStatus.OK);
    }


    @PostMapping("/")
    public String saveTypeRoom(@RequestBody requestTypeRoom typeRoom) {
        typeRoomService.save(typeRoom);
        
        return "Register OK";
    }


    // @PutMapping("/")
    // public String update(@RequestBody requestTypeRoom typeRoom){
    //     typeRoomService.update(typeRoom);
    //    return "Register actualizado correctamente";
    // }

     @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody requestTypeRoom typeRoom){
        typeRoomService.update(id,typeRoom);
       return "Register actualizado correctamente";
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
         responseDTO response= typeRoomService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
     
    

    
}
