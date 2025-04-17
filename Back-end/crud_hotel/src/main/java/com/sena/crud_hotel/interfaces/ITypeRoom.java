package com.sena.crud_hotel.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_hotel.model.typeRoom;

@Repository
public interface ITypeRoom extends JpaRepository<typeRoom,Integer>{

}
