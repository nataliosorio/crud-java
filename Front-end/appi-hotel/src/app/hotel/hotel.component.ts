import { Component, OnInit } from '@angular/core';
import { Hotel, ServiceHotelService } from '../service-hotel.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hotel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.css'
})
export class HotelComponent implements OnInit {

  typeRoom: Hotel [] = [];
  
    constructor(private typeRoomService: ServiceHotelService) {}
  
    ngOnInit(): void {
      this.typeRoomService.getHotel().subscribe(data => {
        this.typeRoom = data;
        console.log(this.typeRoom);
      });
    }
}
