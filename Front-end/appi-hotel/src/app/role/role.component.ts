import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Rol, ServiceRoleService } from '../service-role.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-role',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './role.component.html',
  styleUrl: './role.component.css'
})
export class RoleComponent implements OnInit{
  roles: Rol[] = [];
  rolForm!: FormGroup;
  showForm: boolean = false;
  editIndex: number | null = null;
  isLoading: boolean = false;

  constructor(private rolService: ServiceRoleService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadRoles();
    this.rolForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  loadRoles(): void {
    this.isLoading = true;
    this.rolService.getRol()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Roles cargados:', data);
          this.roles = [...data];
        },
        error: (error) => {
          console.error('Error al cargar roles:', error);
        }
      });
  }

  refrescar(): void {
    this.loadRoles();
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    this.rolForm.reset();
    this.editIndex = null;
  }

  closeForm(): void {
    this.showForm = false;
    this.rolForm.reset();
    this.editIndex = null;
  }

  trackByRolId(index: number, rol: Rol): number {
    return rol.id;
  }

  submitForm(): void {
    if (this.rolForm.valid) {
      const newRol: Omit<Rol, 'id'> = {
        name: this.rolForm.value.name
      };

      this.isLoading = true;
      this.rolService.addRol(newRol)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: (response) => {
            console.log('Rol creado:', response);
            this.roles.push(response);
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al crear rol:', error);
          }
        });
    }
  }

  editRol(index: number): void {
    const rol = this.roles[index];
    this.rolForm.patchValue(rol);
    this.showForm = true;
    this.editIndex = index;
  }

  updateRol(): void {
    if (this.rolForm.valid && this.editIndex !== null) {
      const updatedRol: Rol = {
        id: this.roles[this.editIndex].id,
        name: this.rolForm.value.name
      };

      this.isLoading = true;
      this.rolService.updateRol(updatedRol)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: (response) => {
            console.log('Rol actualizado:', response);
            this.roles[this.editIndex!] = response;
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al actualizar rol:', error);
          }
        });
    }
  }

  deleteRol(index: number): void {
    const rolId = this.roles[index].id;

    this.isLoading = true;
    this.rolService.deleteRol(rolId)
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: () => {
          console.log('Rol eliminado:', rolId);
          this.roles.splice(index, 1);
        },
        error: (error) => {
          console.error('Error al eliminar rol:', error);
        }
      });
  }
}
