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

import com.sena.crud_hotel.DTO.requestDocumentType;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.service.DocumentTypeService;

@RestController
@RequestMapping("api/v1/DocumentType")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTypeRoom() {
        var ListTypeRoom = documentTypeService.finAllTypeRooms();
        return new ResponseEntity<Object>(ListTypeRoom,HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTypeRoom(@PathVariable int id) {
        var ListTypeRoom = documentTypeService.finByIdTypeRooms(id);
        return new ResponseEntity<>(ListTypeRoom,HttpStatus.OK);
    }


    @PostMapping("/")
    public String saveTypeRoom(@RequestBody requestDocumentType typeRoom) {
        documentTypeService.save(typeRoom);
        
        return "Register OK";
    }


    @PutMapping("/")
    public String update(@RequestBody requestDocumentType typeRoom){
        documentTypeService.update(typeRoom);
       return "Register actualizado correctamente";
    }

 

     @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
         responseDTO response= documentTypeService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
