import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee, ServiceEmployeeService } from '../service-employee.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DocumentType, ServiceDocumentTypeService } from '../service-document-type.service';
import { Hotel, ServiceHotelService } from '../service-hotel.service';
import { Rol, ServiceRoleService } from '../service-role.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit{
  employeeForm!: FormGroup;
  employees: Employee[] = [];
  documentTypes: DocumentType [] = [];
  roles: Rol [] = [];
  hotels: Hotel [] = [];

  isLoading = false;
  showForm = false;
  editIndex: number | null = null;

  constructor(
    private fb: FormBuilder,
    private employeeService: ServiceEmployeeService,
    private documentTypeService: ServiceDocumentTypeService,
    private roleService: ServiceRoleService,
    private hotelService: ServiceHotelService
  ) {}

  ngOnInit(): void {
    this.loadEmployees();
    this.loadDocumentTypes();
    this.loadRoles();
    this.loadHotels();

    this.employeeForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      idDocumentType: [null, Validators.required],
      documentNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      idRole: [null, Validators.required],
      idHotel: [null, Validators.required]
    });
  }

  loadEmployees(): void {
    this.isLoading = true;
    this.employeeService.getRooms()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Empleados cargados:', data);
          this.employees = [...data];
        },
        error: (err) => {
          console.error('Error al cargar empleados:', err);
        }
      });
  }

  loadDocumentTypes(): void {
    this.documentTypeService.getCities().subscribe({
      next: (data) => {
        this.documentTypes = data;
      },
      error: (err) => console.error('Error al cargar tipos de documento:', err)
    });
  }

  loadRoles(): void {
    this.roleService.getRol().subscribe({
      next: (data) => {
        this.roles = data;
      },
      error: (err) => console.error('Error al cargar roles:', err)
    });
  }

  loadHotels(): void {
    this.hotelService.getRooms().subscribe({
      next: (data) => {
        this.hotels = data;
      },
      error: (err) => console.error('Error al cargar hoteles:', err)
    });
  }

  refrescar(): void {
    this.loadEmployees();
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    this.employeeForm.reset();
    this.editIndex = null;
  }

  closeForm(): void {
    this.showForm = false;
    this.employeeForm.reset();
    this.editIndex = null;
  }

  trackByEmployeeId(index: number, employee: Employee): number {
    return employee.id;
  }

  submitForm(): void {
    if (this.employeeForm.valid) {
      const newEmployee: Omit<Employee, 'id'> = this.employeeForm.value;

      this.isLoading = true;
      this.employeeService.addRoom(newEmployee)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadEmployees();
            this.closeForm();
          },
          error: (err) => {
            console.error('Error al crear empleado:', err);
          }
        });
    }
  }

  editEmployee(index: number): void {
    const employee = this.employees[index];
    this.editIndex = index;
    this.showForm = true;

    this.employeeForm.patchValue({
      firstName: employee.firstName,
      lastName: employee.lastName,
      idDocumentType: employee.idDocumentType,
      documentNumber: employee.documentNumber,
      email: employee.email,
      phone: employee.phone,
      idRole: employee.idRole,
      idHotel: employee.idHotel
    });
  }

  updateEmployee(): void {
    if (this.editIndex !== null && this.employeeForm.valid) {
      const updatedEmployee: Employee = {
        id: this.employees[this.editIndex].id,
        ...this.employeeForm.value
      };

      this.isLoading = true;
      this.employeeService.updateRoom(updatedEmployee)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadEmployees();
            this.closeForm();
          },
          error: (err) => {
            console.error('Error al actualizar empleado:', err);
          }
        });
    }
  }

  deleteEmployee(id: number): void {
    this.isLoading = true;
    this.employeeService.deleteRoom(id)
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: () => {
          this.loadEmployees();
        },
        error: (err) => {
          console.error('Error al eliminar empleado:', err);
        }
      });
  }
}
