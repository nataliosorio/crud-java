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

import com.sena.crud_hotel.DTO.requestRol;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.service.RolService;

@RestController
@RequestMapping("api/v1/Rol")
public class RolController {

     @Autowired
    private RolService rolService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTypeRoom() {
        var ListTypeRoom = rolService.finAllTypeRooms();
        return new ResponseEntity<Object>(ListTypeRoom,HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTypeRoom(@PathVariable int id) {
        var ListTypeRoom = rolService.finByIdTypeRooms(id);
        return new ResponseEntity<>(ListTypeRoom,HttpStatus.OK);
    }


    @PostMapping("/")
    public String saveTypeRoom(@RequestBody requestRol typeRoom) {
        rolService.save(typeRoom);
        
        return "Register OK";
    }


    // @PutMapping("/")
    // public String update(@RequestBody requestRol typeRoom){
    //     rolService.update(typeRoom);
    //    return "Register actualizado correctamente";
    // }

      @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody requestRol typeRoom){
        rolService.update(id,typeRoom);
       return "Register actualizado correctamente";
    }

    //  @PutMapping("/{id}")
    // public requestRoom updateRoom(@PathVariable int id, @RequestBody requestRoom dto) {
    //     return cityService.update(id, dto);
    // }
  

     @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
         responseDTO response= rolService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
