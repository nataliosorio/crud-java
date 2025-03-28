package com.sena.crud_basic.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// import com.sena.crud_basic.model.Reservation;

public class requestInvoiceDTO {
    private int reservation;
    private LocalDateTime issueDate;
    private BigDecimal totalAmount;

    public requestInvoiceDTO(int reservation, LocalDateTime issueDate, BigDecimal totalAmount) {
        this.reservation = reservation;
        this.issueDate = issueDate;
        this.totalAmount = totalAmount;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getReservation() {
        return reservation;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    
    


}
