package com.sena.crud_basic.service;

// import java.io.ObjectInputFilter.Status;
// import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTOs.requestRoomDTO;
import com.sena.crud_basic.interfaces.IRoom;
import com.sena.crud_basic.model.Room;


@Service
public class RoomService {
 /*
     * @Autowired = Incluye la conexión de la interface
     */
    @Autowired
    private IRoom RoomData;

    public List<Room> findAllRooms() {
        return RoomData.findAll();
    }

    public Optional<Room> findByIdRooms(int id) {
        return RoomData.findById(id);
    }

    public void save(requestRoomDTO room) {
        RoomData.save(convertRegisterToRoom(room));
    }

    public Room convertRegisterToRoom(requestRoomDTO room) {
        return new Room(
        0,  // ID, se autogenerará en la BD
        room.getRoomNumber(),
        "Disponible",  // Se establece el status por defecto
        room.getHotel(),
        room.getRoomType()
    );
    }

    // public void update(int id, tasks taskUpdate) {
    //     var task = findByIdTareas(id);
    //     if (task.isPresent()) {
    //         task.get().setTitle(taskUpdate.getTitle());
    //         task.get().setDescription(taskUpdate.getDescription());
    //         RoomData.save(task.get());

    //     }
    // }

    // public void delete(int id) {
    //     var task = findByIdTareas(id);
    //     if (task.isPresent()) {// trae un boolean si existe o no
    //         RoomData.delete(task.get());
    //     }
    // }
}
