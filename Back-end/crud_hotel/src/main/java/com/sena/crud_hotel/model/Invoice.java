package com.sena.crud_hotel.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private int id;

    @OneToOne
    @JoinColumn(name = "id_reservation", nullable = false, unique = true)
    private Reservation reservation;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal tax;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issueDate;

    // @Column(name = "payment_method", length = 30)
    // private String paymentMethod;

    public Invoice() {
    }

    public Invoice(int id, Reservation reservation, BigDecimal subtotal, BigDecimal tax, BigDecimal total,
            PaymentStatus paymentStatus, LocalDateTime issueDate) {
        this.id = id;
        this.reservation = reservation;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.issueDate = issueDate;
        // this.paymentMethod = paymentMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    // public void setPaymentMethod(String paymentMethod) {
    //     this.paymentMethod = paymentMethod;
    // }

    public int getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
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

    // public String getPaymentMethod() {
    //     return paymentMethod;
    // }

    

}
