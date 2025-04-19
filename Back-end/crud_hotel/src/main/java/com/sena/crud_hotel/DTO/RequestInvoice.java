package com.sena.crud_hotel.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sena.crud_hotel.model.PaymentStatus;


public class RequestInvoice {

    private int id;

    private int reservationId;

    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;

    private PaymentStatus paymentStatus;

    private LocalDateTime issueDate;
    

  
    public RequestInvoice() {
    }

    public RequestInvoice(int id, int reservationId, BigDecimal subtotal, BigDecimal tax, BigDecimal total,
    PaymentStatus paymentStatus, LocalDateTime issueDate) {
        this.id = id;
        this.reservationId = reservationId;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.issueDate = issueDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    
}
