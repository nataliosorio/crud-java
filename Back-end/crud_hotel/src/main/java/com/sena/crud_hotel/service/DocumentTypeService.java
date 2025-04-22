package com.sena.crud_hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestDocumentType;
import com.sena.crud_hotel.DTO.responseDTO;
import com.sena.crud_hotel.interfaces.IDocumentType;
import com.sena.crud_hotel.model.DocumentType;

@Service
public class DocumentTypeService {

    @Autowired
    private IDocumentType documentTypeData; 

    public List<DocumentType> finAllTypeRooms(){
        return documentTypeData.findAll();
    }

    public Optional<DocumentType> finByIdTypeRooms(int id){
        return documentTypeData.findById(id);
    }

    public void save(requestDocumentType typeRoom) {
        documentTypeData.save(converRegisterToTypeRoom(typeRoom));
    }

    public DocumentType converRegisterToTypeRoom(requestDocumentType typeRoom) {
        return new DocumentType(
                0,
                typeRoom.getName()
        );
    }

    

       public requestDocumentType update(int id, requestDocumentType typeRoomUpdate) {
        DocumentType room = documentTypeData.findById(id)
            .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrada con ID: " + id));

        room.setName(typeRoomUpdate.getName());
    

        DocumentType updatedRoom = documentTypeData.save(room);

        requestDocumentType updatedDto = new requestDocumentType();
        updatedDto.setId(updatedRoom.getId());
        updatedDto.setName(updatedRoom.getName());

        return updatedDto;

    }


    public responseDTO delete(int id) {
        var typeRoom = finByIdTypeRooms(id);
        responseDTO response=new responseDTO();
        if (typeRoom.isPresent()) {
            documentTypeData.delete(typeRoom.get());
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
