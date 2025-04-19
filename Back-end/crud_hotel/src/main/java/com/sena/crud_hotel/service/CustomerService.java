package com.sena.crud_hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.requestCustomer;
import com.sena.crud_hotel.interfaces.ICustomer;
import com.sena.crud_hotel.interfaces.IDocumentType;
import com.sena.crud_hotel.model.Customer;
import com.sena.crud_hotel.model.DocumentType;

@Service
public class CustomerService {

      @Autowired
    private ICustomer customerRepository;

    @Autowired
    private IDocumentType documentTypeRepository;


    public List<requestCustomer> getAllCustomersWithDetails() {
        return customerRepository.findAllCustomersWithDetails();
    }

    public requestCustomer getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        requestCustomer dto = new requestCustomer();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());

        dto.setIdDocumentType(customer.getDocumentType().getId());
        dto.setDocumentName(customer.getDocumentType().getName());
        dto.setDocumentNumber(customer.getDocumentNumber());

        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());

        return dto;
    }

    public requestCustomer createCustomer(requestCustomer customerDTO) {
        if (customerDTO.getIdDocumentType() <= 0 ) {
            throw new RuntimeException("ID inválidos para tipo de documento.");
        }

        DocumentType documentType = documentTypeRepository.findById(customerDTO.getIdDocumentType())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
        

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setDocumentType(documentType);
        customer.setDocumentNumber(customerDTO.getDocumentNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        Customer savedCustomer = customerRepository.save(customer);

        // Mapear al DTO de respuesta
        requestCustomer responseDTO = new requestCustomer();
        responseDTO.setId(savedCustomer.getId());
        responseDTO.setFirstName(savedCustomer.getFirstName());
        responseDTO.setLastName(savedCustomer.getLastName());
        responseDTO.setIdDocumentType(savedCustomer.getDocumentType().getId());
        responseDTO.setDocumentName(savedCustomer.getDocumentType().getName());
        responseDTO.setDocumentNumber(savedCustomer.getDocumentNumber());
        responseDTO.setEmail(savedCustomer.getEmail());
        responseDTO.setPhone(savedCustomer.getPhone());
       

        return responseDTO;
    }

    public requestCustomer updateCustomer(int id, requestCustomer customerDTO) {
        // Buscar el cliente existente
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    
        // Validar y asignar el tipo de documento
        DocumentType documentType = documentTypeRepository.findById(customerDTO.getIdDocumentType())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado con ID: " + customerDTO.getIdDocumentType()));
    
        // Actualizar campos del cliente
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setDocumentType(documentType);
        customer.setDocumentNumber(customerDTO.getDocumentNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
    
        // Guardar cambios en la base de datos
        Customer updatedCustomer = customerRepository.save(customer);
    
        // Convertir el cliente actualizado a DTO y retornar
        return mapToRequestCustomer(updatedCustomer);
    }
    private requestCustomer mapToRequestCustomer(Customer customer) {
        requestCustomer dto = new requestCustomer();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setIdDocumentType(customer.getDocumentType().getId());
        dto.setDocumentName(customer.getDocumentType().getName());
        dto.setDocumentNumber(customer.getDocumentNumber());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
    
    public void deleteCustomer(int id) {
        // Verificar si el cliente existe
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    
        // Eliminar el cliente
        customerRepository.delete(customer);
    }
    
}
