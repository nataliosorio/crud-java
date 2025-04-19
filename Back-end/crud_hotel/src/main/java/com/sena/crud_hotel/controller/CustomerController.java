package com.sena.crud_hotel.controller;

import java.util.List;

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

import com.sena.crud_hotel.DTO.requestCustomer;
import com.sena.crud_hotel.service.CustomerService;

@RestController
@RequestMapping("api/v1/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerServiceService;


     @GetMapping("/")
    public List<requestCustomer> getAllRooms() {
        return customerServiceService.getAllCustomersWithDetails();
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<requestCustomer> getEmployeeById(@PathVariable int id) {
        requestCustomer employee = customerServiceService.getCustomerById(id);
        return ResponseEntity.ok(employee);
    }

    // Crear empleado
    @PostMapping("/")
    public ResponseEntity<requestCustomer> createEmployee(@RequestBody requestCustomer employeeDTO) {
        requestCustomer created = customerServiceService.createCustomer(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<requestCustomer> updateEmployee(@PathVariable int id, @RequestBody requestCustomer employeeDTO) {
        requestCustomer updated = customerServiceService.updateCustomer(id, employeeDTO);
        return ResponseEntity.ok(updated);
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        customerServiceService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
