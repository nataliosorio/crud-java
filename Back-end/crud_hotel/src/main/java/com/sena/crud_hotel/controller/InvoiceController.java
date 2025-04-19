package com.sena.crud_hotel.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_hotel.model.Invoice;
import com.sena.crud_hotel.service.InvoiceService;

@RestController
@RequestMapping("api/v1/Invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Obtener todas las facturas
    @GetMapping("/")
    public ResponseEntity<Iterable<Invoice>> getAllInvoices() {
        Iterable<Invoice> invoices = invoiceService.findAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Obtener una factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable int id) {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    // Actualizar impuesto de una factura
    @PatchMapping("/{id}/tax")
    public ResponseEntity<Invoice> updateInvoiceTax(
            @PathVariable int id,
            @RequestBody UpdateTaxRequest request) {
        Invoice updatedInvoice = invoiceService.updateInvoice(id, request.getTax());
        return ResponseEntity.ok(updatedInvoice);
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    // Clase auxiliar para la actualización del impuesto
    public static class UpdateTaxRequest {
        private BigDecimal tax;

        public BigDecimal getTax() {
            return tax;
        }

        public void setTax(BigDecimal tax) {
            this.tax = tax;
        }
    }
}
