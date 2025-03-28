package com.sena.crud_basic.model;

// import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "document_types")
@Getter
@Setter
public class Document_type {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToOne(mappedBy = "documentType")
    private Customer customer;

    @OneToOne(mappedBy = "documentType")
    private Employee employee;

    // Getters y Setters
}

