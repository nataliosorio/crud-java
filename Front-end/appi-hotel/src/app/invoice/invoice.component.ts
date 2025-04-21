import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Invoice, ServiceInvoiceService } from '../service-invoice.service';

@Component({
  selector: 'app-invoice',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './invoice.component.html',
  styleUrl: './invoice.component.css'
})
export class InvoiceComponent implements OnInit{

    typeRoom: Invoice [] = [];
    
      constructor(private typeRoomService: ServiceInvoiceService) {}
    
      ngOnInit(): void {
        this.typeRoomService.getHotel().subscribe(data => {
          this.typeRoom = data;
          console.log(this.typeRoom);
        });
      }
}
