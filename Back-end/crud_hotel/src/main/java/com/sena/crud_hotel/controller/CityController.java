package com.sena.crud_hotel.controller;

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

import com.sena.crud_hotel.DTO.requestCity;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.service.CityService;

@RestController
@RequestMapping("api/v1/City")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTypeRoom() {
        var ListTypeRoom = cityService.finAllTypeRooms();
        return new ResponseEntity<Object>(ListTypeRoom,HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTypeRoom(@PathVariable int id) {
        var ListTypeRoom = cityService.finByIdTypeRooms(id);
        return new ResponseEntity<>(ListTypeRoom,HttpStatus.OK);
    }


    @PostMapping("/")
    public String saveTypeRoom(@RequestBody requestCity typeRoom) {
        cityService.save(typeRoom);
        
        return "Register OK";
    }


    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody requestCity typeRoom){
        cityService.update(id,typeRoom);
       return "Register actualizado correctamente";
    }

   


     @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
         responseDTO response= cityService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
