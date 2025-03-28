package com.sena.crud_basic.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// import java.util.List;

// import com.sena.crud_basic.model.Customer;
// import com.sena.crud_basic.model.PaymentMethod;
// import com.sena.crud_basic.model.ReservationRoom;

public class requestReservationDTO {

    private int id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal totalPrice;
    private int customer;
    private int paymentMethod;
    
    // private List<ReservationRoom> reservationRooms;
    public requestReservationDTO(int id, LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal totalPrice,
            int customer, int paymentMethod) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getCustomer() {
        return customer;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }
    
    

}
