import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Invoice } from '../service-invoice.service';
import { Reservation, ServiceReservationService } from '../service-reservation.service';

@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements OnInit {

  typeRoom: Reservation [] = [];
      
        constructor(private typeRoomService: ServiceReservationService) {}
      
        ngOnInit(): void {
          this.typeRoomService.getHotel().subscribe(data => {
            this.typeRoom = data;
            console.log(this.typeRoom);
          });
        }
}
