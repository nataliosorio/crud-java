package com.sena.crud_hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestEmployee;
import com.sena.crud_hotel.interfaces.IDocumentType;
import com.sena.crud_hotel.interfaces.IEmployee;
import com.sena.crud_hotel.interfaces.IHotel;
import com.sena.crud_hotel.interfaces.IRol;
import com.sena.crud_hotel.model.DocumentType;
import com.sena.crud_hotel.model.Employee;
import com.sena.crud_hotel.model.Hotel;
import com.sena.crud_hotel.model.Role;

@Service
public class EmployeeService{

    @Autowired
    private IEmployee employeeRepository;

    @Autowired
    private IDocumentType documentTypeRepository;

    @Autowired
    private IRol roleRepository;

    @Autowired
    private IHotel hotelRepository;


    public List<requestEmployee> getAllRoomsWithDetails() {
        return employeeRepository.findAllEmployeesWithDetails();
    }

    public requestEmployee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    
        requestEmployee dto = new requestEmployee();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
    
        dto.setIdDocumentType(employee.getDocumentType().getId());
        dto.setDocumentTypeName(employee.getDocumentType().getName());
        dto.setDocumentNumber(employee.getDocumentNumber());
    
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
    
        dto.setIdRole(employee.getRole().getId());
        dto.setRolName(employee.getRole().getName());
    
        dto.setIdHotel(employee.getHotelE().getId());
        dto.setHotelName(employee.getHotelE().getName());
    
        return dto;
    }
    
    public requestEmployee createEmployee(requestEmployee employeeDTO) {
        // Validación de IDs
        if (employeeDTO.getIdDocumentType() <= 0 || employeeDTO.getIdRole() <= 0 || employeeDTO.getIdHotel() <= 0) {
            throw new RuntimeException("IDs inválidos para tipo de documento, rol o hotel.");
        }
    
        // Buscar entidades relacionadas
        DocumentType documentType = documentTypeRepository.findById(employeeDTO.getIdDocumentType())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado con ID: " + employeeDTO.getIdDocumentType()));
    
        Role role = roleRepository.findById(employeeDTO.getIdRole())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + employeeDTO.getIdRole()));
    
        Hotel hotel = hotelRepository.findById(employeeDTO.getIdHotel())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + employeeDTO.getIdHotel()));
    
        // Crear instancia de Employee
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDocumentNumber(employeeDTO.getDocumentNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDocumentType(documentType);
        employee.setRole(role);
        employee.setHotelE(hotel);
    
        // Guardar en BD
        Employee savedEmployee = employeeRepository.save(employee);
    
        // Convertir a DTO y retornar
        return convertToDTO(savedEmployee);
    }
    

    public requestEmployee convertToDTO(Employee employee) {
        return new requestEmployee(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getDocumentType().getId(),
            employee.getDocumentType().getName(),
            employee.getDocumentNumber(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getRole().getId(),
            employee.getRole().getName(),
            employee.getHotelE().getId(),
            employee.getHotelE().getName()
        );
    }

    public requestEmployee updateEmployee(int id, requestEmployee employeeDTO) {
        // Buscar el empleado existente
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    
        // Validar y asignar relaciones
        DocumentType documentType = documentTypeRepository.findById(employeeDTO.getIdDocumentType())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado con ID: " + employeeDTO.getIdDocumentType()));
    
        Role role = roleRepository.findById(employeeDTO.getIdRole())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + employeeDTO.getIdRole()));
    
        Hotel hotel = hotelRepository.findById(employeeDTO.getIdHotel())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + employeeDTO.getIdHotel()));
    
        // Actualizar campos
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDocumentType(documentType);
        employee.setDocumentNumber(employeeDTO.getDocumentNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setRole(role);
        employee.setHotelE(hotel);
    
        // Guardar cambios
        Employee updatedEmployee = employeeRepository.save(employee);
    
        // Convertir a DTO y retornar
        return convertToDTO(updatedEmployee);
    }

    public void deleteEmployee(int id) {
        // Verificar si el empleado existe
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    
        // Eliminar el empleado
        employeeRepository.delete(employee);
    }
    

    

}
