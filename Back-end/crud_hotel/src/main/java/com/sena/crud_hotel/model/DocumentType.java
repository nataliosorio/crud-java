package com.sena.crud_hotel.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "document_type")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document_type", nullable = false)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    // Relación con empleados
    @OneToMany(mappedBy = "documentType")
    private List<Employee> employees;

    public DocumentType() {
    }

    public DocumentType(int id, String name) {
        this.id = id;
        this.name = name;
        // this.employees = employees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

  
    
    
}
