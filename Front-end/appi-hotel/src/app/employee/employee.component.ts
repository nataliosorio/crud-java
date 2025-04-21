import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee, ServiceEmployeeService } from '../service-employee.service';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit{
  typeRoom: Employee [] = [];
  
    constructor(private typeRoomService: ServiceEmployeeService) {}
  
    ngOnInit(): void {
      this.typeRoomService.getHotel().subscribe(data => {
        this.typeRoom = data;
        console.log(this.typeRoom);
      });
    }
}
