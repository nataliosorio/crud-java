package com.sena.crud_basic.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTOs.requestRoleDTO;
import com.sena.crud_basic.DTOs.responseRoleDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/Rols")
public class RolController {
    private final List<requestRoleDTO> typeRooms = new ArrayList<>();

    // @PostMapping("/create")
    // public ResponseEntity<Object> createTypeRoom(@RequestBody requestRoleDTO request) {
    //     responseRoleDTO response = new responseRoleDTO(
    //         "Tipo de habitación creado: " + request.getName() + " - " + request.getDescription()
    //     );
    //     return new ResponseEntity<>(response, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<responseRoleDTO> createTypeRoom(@RequestBody requestRoleDTO request) {
        typeRooms.add(request);
        responseRoleDTO response = new responseRoleDTO("Rol creado correctamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTypeRoomById(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            return new ResponseEntity<>(typeRooms.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoleDTO("Rol no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<requestRoleDTO>> getAllTypeRooms() {
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<responseRoleDTO> updateTypeRoom(@PathVariable int id, @RequestBody requestRoleDTO request) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.set(id, request);
            return new ResponseEntity<>(new responseRoleDTO("Rol actualizado correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoleDTO("Rol no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseRoleDTO> deleteTypeRoom(@PathVariable int id) {
        if (id >= 0 && id < typeRooms.size()) {
            typeRooms.remove(id);
            return new ResponseEntity<>(new responseRoleDTO("Rol eliminado correctamente."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseRoleDTO("Rol no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    
}


