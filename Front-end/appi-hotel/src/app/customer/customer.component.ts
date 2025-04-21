import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Customer, ServiceCustomerService } from '../service-customer.service';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit {
typeRoom: Customer [] = [];
  
    constructor(private typeRoomService: ServiceCustomerService) {}
  
    ngOnInit(): void {
      this.typeRoomService.getHotel().subscribe(data => {
        this.typeRoom = data;
        console.log(this.typeRoom);
      });
    }
}
