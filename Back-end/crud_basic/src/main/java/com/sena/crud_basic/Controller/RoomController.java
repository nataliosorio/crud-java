package com.sena.crud_basic.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTOs.requestRoomDTO;
import com.sena.crud_basic.DTOs.responseRoomDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/Rooms")
public class RoomController {
    private final List<requestRoomDTO> typeRooms = new ArrayList<>();

    // @PostMapping("/create")
    // public ResponseEntity<Object> createTypeRoom(@RequestBody requestRoomDTO request) {
    //     responseRoomDTO response = new responseRoomDTO(
    //         "Tipo de habitación creado: " + request.getName() + " - " + request.getDescription()
    //     );
    //     return new ResponseEntity<>(response, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<responseRoomDTO> createTypeRoom(@RequestBody requestRoomDTO request) {
        typeRooms.add(request);
        responseRoomDTO response = new responseRoomDTO("Habitación creada correctamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeRoomById(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            return new ResponseEntity<>(typeRooms.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoomDTO("Habitación no encontrada."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<requestRoomDTO>> getAllTypeRooms() {
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseRoomDTO> updateTypeRoom(@PathVariable int id, @RequestBody requestRoomDTO request) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.set(id, request);
            return new ResponseEntity<>(new responseRoomDTO("Habitación actualizada correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoomDTO("Habitación no encontrada."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseRoomDTO> deleteTypeRoom(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.remove(id);
            return new ResponseEntity<>(new responseRoomDTO("Habitación eliminada correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoomDTO("Habitación no encontrada."), HttpStatus.NOT_FOUND);
        }
    }

    
}


