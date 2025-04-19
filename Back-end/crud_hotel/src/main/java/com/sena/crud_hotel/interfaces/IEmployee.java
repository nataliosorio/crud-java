package com.sena.crud_hotel.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_hotel.DTO.requestEmployee;
import com.sena.crud_hotel.model.Employee;

public interface IEmployee extends JpaRepository<Employee, Integer> {


   @Query("SELECT new com.sena.crud_hotel.DTO.requestEmployee(" +
       "e.id, e.firstName, e.lastName, " +
       "dt.id, dt.name, " +
       "e.documentNumber, e.email, e.phone, " +
       "r.id, r.name, " +
       "h.id, h.name) " +
       "FROM Employee e " +
       "JOIN e.documentType dt " +
       "JOIN e.role r " +
       "JOIN e.hotelE h")
List<requestEmployee> findAllEmployeesWithDetails();



}
