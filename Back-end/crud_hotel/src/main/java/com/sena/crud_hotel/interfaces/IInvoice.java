package com.sena.crud_hotel.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.model.PaymentStatus;

public interface IInvoice extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findByReservationId(int reservationId);
    List<Invoice> findByPaymentStatus(PaymentStatus paymentStatus);
    
    // Obtener facturas con detalles completos
    @Query("SELECT i FROM Invoice i JOIN FETCH i.reservation r JOIN FETCH r.employee WHERE i.id = :id")
    Optional<Invoice> findByIdWithDetails(@Param("id") int id);
    
    // Consulta para el reporte de facturación por período
    @Query("SELECT i FROM Invoice i WHERE i.issueDate BETWEEN :startDate AND :endDate")
    List<Invoice> findInvoicesByDateRange(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );
    
    // Obtener el total facturado por estado de pago
    @Query("SELECT i.paymentStatus, SUM(i.total) FROM Invoice i GROUP BY i.paymentStatus")
    List<Object[]> getTotalAmountByPaymentStatus();
}
