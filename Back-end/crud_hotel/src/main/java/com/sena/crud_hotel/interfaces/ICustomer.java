package com.sena.crud_hotel.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_hotel.DTO.requestCustomer;
import com.sena.crud_hotel.model.Customer;

public interface ICustomer extends JpaRepository<Customer, Integer>{

    @Query("SELECT new com.sena.crud_hotel.DTO.requestCustomer(c.id, c.firstName, c.lastName, c.documentType.id, c.documentType.name, c.documentNumber, c.phone, c.email) " +
           "FROM Customer c JOIN c.documentType dt")
    List<requestCustomer> findAllCustomersWithDetails();

}
