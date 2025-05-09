package com.sena.crud_hotel.DTO;

import java.time.LocalDateTime;
import java.util.List;




public class requestReservation {

    
    private int id;

    // Datos del cliente
    private int customerId;
    private String customerFullName;

    // Datos del empleado
    private int employeeId;
    private String employeeFullName;

    private int numberday;
    private int numberNight;

    private String status;
    private String notes;
    private LocalDateTime createdAt;

    private List<RequestReservationRoom> rooms;

    private RequestInvoice invoice;

    // Constructor vacío (recomendado para frameworks como Jackson)
    public requestReservation() {}

    // Constructor completo
    public requestReservation(int id,
        int customerId,
        String customerFullName,
        int employeeId,
        String employeeFullName,
        int numberday,
        int numberNight,
        String status,
        String notes,
        LocalDateTime createdAt,
        List<RequestReservationRoom> rooms,
        RequestInvoice invoice) {
            this.id = id;
            this.customerId = customerId;
            this.customerFullName = customerFullName;
            this.employeeId = employeeId;
            this.employeeFullName = employeeFullName;
            this.numberday = numberday;
            this.numberNight = numberNight;
            this.status = status;
            this.notes = notes;
            this.createdAt = createdAt;
            this.rooms = rooms;
            this.invoice = invoice;
    }

    // Getters y Setters (puedes generarlos automáticamente con tu IDE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    
   
    public int getNumberday() {
        return numberday;
    }

    public int getNumberNight() {
        return numberNight;
    }

    public void setNumberday(int numberday) {
        this.numberday = numberday;
    }

    public void setNumberNight(int numberNight) {
        this.numberNight = numberNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<RequestReservationRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<RequestReservationRoom> rooms) {
        this.rooms = rooms;
    }

    public RequestInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(RequestInvoice invoice) {
        this.invoice = invoice;
    }
    


   
    
}

   