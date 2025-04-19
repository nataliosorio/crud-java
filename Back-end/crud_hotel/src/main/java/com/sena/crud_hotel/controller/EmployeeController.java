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

import com.sena.crud_hotel.DTO.requestEmployee;
import com.sena.crud_hotel.service.EmployeeService;

@RestController
@RequestMapping("api/v1/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


     @GetMapping("/")
    public List<requestEmployee> getAllRooms() {
        return employeeService.getAllRoomsWithDetails();
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<requestEmployee> getEmployeeById(@PathVariable int id) {
        requestEmployee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // Crear empleado
    @PostMapping("/")
    public ResponseEntity<requestEmployee> createEmployee(@RequestBody requestEmployee employeeDTO) {
        requestEmployee created = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<requestEmployee> updateEmployee(@PathVariable int id, @RequestBody requestEmployee employeeDTO) {
        requestEmployee updated = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updated);
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
