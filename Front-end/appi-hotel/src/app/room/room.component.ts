import { Component, OnInit } from '@angular/core';
import { Room, ServiceRoomService } from '../service-room.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {
typeRoom: Room [] = [];
  
    constructor(private typeRoomService: ServiceRoomService) {}
  
    ngOnInit(): void {
      this.typeRoomService.getRoom().subscribe(data => {
        this.typeRoom = data;
        console.log(this.typeRoom);
      });
    }
}
