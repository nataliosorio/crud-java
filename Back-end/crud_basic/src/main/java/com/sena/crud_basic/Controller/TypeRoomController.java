package com.sena.crud_basic.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTOs.requestTypeRoomDTO;
import com.sena.crud_basic.DTOs.responseTypeRoomDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/typeRooms")
public class TypeRoomController {
    private final List<requestTypeRoomDTO> typeRooms = new ArrayList<>();

    // @PostMapping("/create")
    // public ResponseEntity<Object> createTypeRoom(@RequestBody requestTypeRoomDTO request) {
    //     responseTypeRoomDTO response = new responseTypeRoomDTO(
    //         "Tipo de habitación creado: " + request.getName() + " - " + request.getDescription()
    //     );
    //     return new ResponseEntity<>(response, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<responseTypeRoomDTO> createTypeRoom(@RequestBody requestTypeRoomDTO request) {
        typeRooms.add(request);
        responseTypeRoomDTO response = new responseTypeRoomDTO("Tipo de habitación creado correctamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeRoomById(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            return new ResponseEntity<>(typeRooms.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseTypeRoomDTO("Tipo de habitación no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<requestTypeRoomDTO>> getAllTypeRooms() {
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseTypeRoomDTO> updateTypeRoom(@PathVariable int id, @RequestBody requestTypeRoomDTO request) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.set(id, request);
            return new ResponseEntity<>(new responseTypeRoomDTO("Tipo de habitación actualizado correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseTypeRoomDTO("Tipo de habitación no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseTypeRoomDTO> deleteTypeRoom(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.remove(id);
            return new ResponseEntity<>(new responseTypeRoomDTO("Tipo de habitación eliminado correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseTypeRoomDTO("Tipo de habitación no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    
}


