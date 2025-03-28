package com.sena.crud_basic.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTOs.requestReservationRoomDTO;
import com.sena.crud_basic.DTOs.responseReservationRoomDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/Rols")
public class ReservationRoomController {
    private final List<requestReservationRoomDTO> typeRooms = new ArrayList<>();

    // @PostMapping("/create")
    // public ResponseEntity<Object> createTypeRoom(@RequestBody requestReservationRoomDTO request) {
    //     responseReservationRoomDTO response = new responseReservationRoomDTO(
    //         "Tipo de habitación creado: " + request.getName() + " - " + request.getDescription()
    //     );
    //     return new ResponseEntity<>(response, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<responseReservationRoomDTO> createTypeRoom(@RequestBody requestReservationRoomDTO request) {
        typeRooms.add(request);
        responseReservationRoomDTO response = new responseReservationRoomDTO("Reserva de habitacion correctamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeRoomById(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            return new ResponseEntity<>(typeRooms.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseReservationRoomDTO("Reserva de habitacion encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<requestReservationRoomDTO>> getAllTypeRooms() {
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseReservationRoomDTO> updateTypeRoom(@PathVariable int id, @RequestBody requestReservationRoomDTO request) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.set(id, request);
            return new ResponseEntity<>(new responseReservationRoomDTO("Reserva de habtacion correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseReservationRoomDTO("Reserva de habitacion encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseReservationRoomDTO> deleteTypeRoom(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.remove(id);
            return new ResponseEntity<>(new responseReservationRoomDTO("Reserva de habitación eliminada correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseReservationRoomDTO("Reserva de habitación no encontrada."), HttpStatus.NOT_FOUND);
        }
    }

    
}

