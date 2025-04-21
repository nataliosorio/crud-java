import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Rol, ServiceRoleService } from '../service-role.service';

@Component({
  selector: 'app-role',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './role.component.html',
  styleUrl: './role.component.css'
})
export class RoleComponent implements OnInit{
  rol: Rol [] = [];

  constructor(private RolService: ServiceRoleService) {}

  ngOnInit(): void {
    this.RolService.getRol().subscribe(data => {
      this.rol = data;
      console.log(this.rol);
    });
  }
}
