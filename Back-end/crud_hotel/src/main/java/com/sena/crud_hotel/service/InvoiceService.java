package com.sena.crud_hotel.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_hotel.DTO.RequestInvoice;
import com.sena.crud_hotel.interfaces.IInvoice;
// import com.sena.crud_hotel.interfaces.IReservation;
import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.model.PaymentStatus;
// import com.sena.crud_hotel.model.Reservation;
import com.sena.crud_hotel.model.Reservation;

@Service

public class InvoiceService {

     @Autowired
    private IInvoice invoiceRepository;

        // Método para generar factura asociada a una reserva
        public Invoice generateInvoice(Reservation reservation, BigDecimal subtotal) {

            // Verificar si ya existe una factura
            Optional<Invoice> existingInvoiceOpt = invoiceRepository.findByReservationId(reservation.getId());

            Invoice invoice;
            if (existingInvoiceOpt.isPresent()) {
                invoice = existingInvoiceOpt.get();
            } else {
                invoice = new Invoice();
                invoice.setReservation(reservation);
            }

            invoice.setSubtotal(subtotal);
            invoice.setTax(subtotal.multiply(BigDecimal.valueOf(0.16)));
            invoice.setTotal(subtotal.add(invoice.getTax()));
            invoice.setIssueDate(reservation.getCreatedAt());
            invoice.setPaymentStatus(PaymentStatus.PENDING);

            return invoiceRepository.save(invoice);
        }

    // Método para obtener una factura por ID
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    // Método para consultar todas las facturas
    // public Iterable<Invoice> findAllInvoices() {
    //     return invoiceRepository.findAll();
    // }

    public List<RequestInvoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return invoices.stream().map(invoice -> new RequestInvoice(
                invoice.getId(),
                invoice.getReservation().getId(), // sacamos solo el ID
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getTotal(),
                invoice.getPaymentStatus(),
                invoice.getIssueDate()
        )).collect(Collectors.toList());
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
