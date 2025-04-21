import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ServiceTyperoomService, typeRoom } from '../service-typeroom.service';

@Component({
  selector: 'app-type-room',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './type-room.component.html',
  styleUrl: './type-room.component.css'
})
export class TypeRoomComponent implements OnInit{
 typeRoom: typeRoom [] = [];

  constructor(private typeRoomService: ServiceTyperoomService) {}

  ngOnInit(): void {
    this.typeRoomService.getTypeRoom().subscribe(data => {
      this.typeRoom = data;
      console.log(this.typeRoom);
    });
  }
  

}
