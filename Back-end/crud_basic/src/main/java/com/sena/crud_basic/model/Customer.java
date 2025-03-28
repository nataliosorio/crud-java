package com.sena.crud_basic.model;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

     @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "document_type_id", unique = true)
    private Document_type documentType;

    @Column(name = "document_number", nullable = false, unique = true, length = 20)
    private String documentNumber;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;
    
    
}