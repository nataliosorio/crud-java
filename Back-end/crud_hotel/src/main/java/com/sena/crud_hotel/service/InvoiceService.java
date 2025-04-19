package com.sena.crud_hotel.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.interfaces.IInvoice;
// import com.sena.crud_hotel.interfaces.IReservation;
import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.model.PaymentStatus;
// import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.model.Reservation;

@Service

public class InvoiceService {

    //  @Autowired
    // private IInvoice invoiceRepository;
    
    // @Autowired
    // // private IReservation reservationRepository;
    
    // /**
    //  * CRUD: Obtener una factura por ID
    //  */
    // public RequestInvoice getInvoiceById(int id) {
    //     Invoice invoice = invoiceRepository.findById(id)
    //         .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada"));
    //     return convertToDTO(invoice);
    // }
    
    // /**
    //  * CRUD: Obtener factura por ID de reservación
    //  */
    // public RequestInvoice getInvoiceByReservationId(int reservationId) {
    //     Invoice invoice = invoiceRepository.findByReservationId(reservationId)
    //         .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada para la reservación"));
    //     return convertToDTO(invoice);
    // }
    
    // /**
    //  * CRUD: Listar todas las facturas
    //  */
    // public List<RequestInvoice> getAllInvoices() {
    //     List<Invoice> invoices = invoiceRepository.findAll();
    //     return invoices.stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
    
    // /**
    //  * CRUD: Listar facturas por estado de pago
    //  */
    // public List<RequestInvoice> getInvoicesByPaymentStatus(String status) {
    //     List<Invoice> invoices = invoiceRepository.findByPaymentStatus(PaymentStatus.valueOf(status));
    //     return invoices.stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
    
    // /**
    //  * CRUD: Actualizar el estado de pago de una factura
    //  */
    // public RequestInvoice updateInvoicePaymentStatus(int id, String paymentStatus) {
    //     Invoice invoice = invoiceRepository.findById(id)
    //         .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada"));
        
    //     invoice.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
    //     invoice = invoiceRepository.save(invoice);
        
    //     return convertToDTO(invoice);
    // }
    
    // /**
    //  * Generar reporte de facturación por rango de fechas
    //  */
    // public List<RequestInvoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    //     List<Invoice> invoices = invoiceRepository.findInvoicesByDateRange(startDate, endDate);
    //     return invoices.stream()
    //         .map(this::convertToDTO)
    //         .collect(Collectors.toList());
    // }
    
    // /**
    //  * Obtener resumen de facturación por estado de pago
    //  */
    // public Map<String, BigDecimal> getTotalsByPaymentStatus() {
    //     List<Object[]> results = invoiceRepository.getTotalAmountByPaymentStatus();
    //     Map<String, BigDecimal> totals = new HashMap<>();
        
    //     for (Object[] result : results) {
    //         PaymentStatus status = (PaymentStatus) result[0];
    //         BigDecimal total = (BigDecimal) result[1];
    //         totals.put(status.toString(), total);
    //     }
        
    //     return totals;
    // }
    
    // /**
    //  * Convertir una entidad Invoice a su DTO
    //  */
    // private RequestInvoice convertToDTO(Invoice invoice) {
    //     RequestInvoice dto = new RequestInvoice();
    //     dto.setId(invoice.getId());
    //     dto.setIdReservation(invoice.getReservation().getId());
    //     dto.setSubtotal(invoice.getSubtotal());
    //     dto.setTax(invoice.getTax());
    //     dto.setTotal(invoice.getTotal());
    //     dto.setPaymentStatus(invoice.getPaymentStatus().toString());
    //     dto.setIssueDate(invoice.getIssueDate());
        
    //     // Agregar datos adicionales de la reservación
    //     // Reservation reservation = invoice.getReservation();
    //     // dto.setClientName(reservation.getCustomer());
    //     // dto.setCheckInDate(reservation.getCheckInDate());
    //     // dto.setCheckOutDate(reservation.getCheckOutDate());
        
    //     return dto;
    // }

    //  private final IInvoice invoiceRepository;

    // public InvoiceService(IInvoice invoiceRepository) {
    //     this.invoiceRepository = invoiceRepository;
    // }

    // public Invoice generateInvoice(Reservation reservation, BigDecimal subtotal) {
    //     BigDecimal taxRate = new BigDecimal("0.18"); // 18% de IGV (puedes ajustar esto)
    //     BigDecimal tax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
    //     BigDecimal total = subtotal.add(tax);

    //     Invoice invoice = new Invoice();
    //     invoice.setReservation(reservation);
    //     invoice.setSubtotal(subtotal);
    //     invoice.setTax(tax);
    //     invoice.setTotal(total);
    //     invoice.setPaymentStatus(PaymentStatus.PENDING);
    //     invoice.setIssueDate(LocalDateTime.now());
    //     // invoice.setPaymentMethod("Efectivo"); // o null si aún no se elige

    //     return invoiceRepository.save(invoice);
    // }


     @Autowired
    private IInvoice invoiceRepository;

        // Método para generar factura asociada a una reserva
        public Invoice generateInvoice(Reservation reservation, BigDecimal subtotal) {
        // 1. Crear la factura con los datos de la reserva
        Invoice invoice = new Invoice();
        invoice.setReservation(reservation);
        invoice.setSubtotal(subtotal);
        invoice.setTax(subtotal.multiply(BigDecimal.valueOf(0.16))); // Suponiendo un 16% de IVA
        invoice.setTotal(subtotal.add(invoice.getTax()));
        invoice.setIssueDate(reservation.getCreatedAt());

        // ❗ Agrega esto:
        invoice.setPaymentStatus(PaymentStatus.PENDING); // o el estado que desees por defecto

        // 2. Guardar la factura en la base de datos
        invoice = invoiceRepository.save(invoice);

        return invoice;
    }

    // Método para obtener una factura por ID
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    // Método para consultar todas las facturas
    public Iterable<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Método para eliminar una factura
    public void deleteInvoice(int id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        
        // Eliminar la factura
        invoiceRepository.delete(invoice);
    }

    public Invoice updateInvoice(int id, BigDecimal tax) {
        // Buscar la factura por su ID
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    
        // Calcular el total basado en el subtotal ya guardado
        BigDecimal subtotal = invoice.getSubtotal();
        BigDecimal total = subtotal.add(tax);  // El total se calcula sumando el impuesto al subtotal
    
        // Actualizar el impuesto y el total
        invoice.setTax(tax);
        invoice.setTotal(total);
    
        // Guardar los cambios en la base de datos
        return invoiceRepository.save(invoice);
    }
    


}
