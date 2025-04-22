import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Customer, ServiceCustomerService } from '../service-customer.service';
import { DocumentType, ServiceDocumentTypeService } from '../service-document-type.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit {
customers: Customer [] = [];
 typeDocument: DocumentType [] = [];

 customerForm!: FormGroup;
  showForm: boolean = false;
  editIndex: number | null = null;
  isLoading: boolean = false;
  
    constructor(private typeRoomService: ServiceCustomerService, private documetnType: ServiceDocumentTypeService, private fb: FormBuilder ) {}
  
   
    ngOnInit(): void {
      this.loadCustomer();
      this.loadTypeDocument();
      this.customerForm = this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        idDocumentType: [null, Validators.required],
        documentNumber: ['', Validators.required],
        phone: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
      });
    }

    loadCustomer(): void {
        this.isLoading = true;
        this.typeRoomService.getRooms()
          .pipe(finalize(() => this.isLoading = false))
          .subscribe({
            next: (data) => {
              console.log('Hoteles cargados:', data);
              this.customers = [...data];
            },
            error: (error) => {
              console.error('Error al cargar lo usuarios:', error);
            }
          });
      }


      loadTypeDocument(): void {
          this.documetnType.getCities().subscribe({
            next: (data) => {
              this.typeDocument = data;
            },
            error: (err) => console.error('Error al cargar las tipos de documentos:', err)
          });
        }
      
        refrescar(): void {
          this.loadCustomer();
        }
      
        toggleForm(): void {
          this.showForm = !this.showForm;
          this.customerForm.reset();
          this.editIndex = null;
        }
      
        closeForm(): void {
          this.showForm = false;
          this.customerForm.reset();
          this.editIndex = null;
        }
      
        trackByHotelId(index: number, hotel: Customer): number {
          return hotel.id;
        }

        submitForm(): void {
            if (this.customerForm.valid) {
              const newHotel: Omit<Customer, 'id'> = this.customerForm.value;
        
              this.isLoading = true;
              this.typeRoomService.addRoom(newHotel)
                .pipe(finalize(() => this.isLoading = false))
                .subscribe({
                  next: () => {
                    this.loadCustomer();
                    this.closeForm();
                  },
                  error: (error) => {
                    console.error('Error al agregar el usuario:', error);
                  }
                });
            }
          }

          editHotel(index: number): void {
            const custmer = this.customers[index];
            this.customerForm.patchValue(custmer);
            this.showForm = true;
            this.editIndex = index;
          }

           updateHotel(): void {
              if (this.customerForm.valid && this.editIndex !== null) {
                const updatedHotel: Customer = {
                  ...this.customers[this.editIndex],
                  ...this.customerForm.value
                };
          
                this.isLoading = true;
                this.typeRoomService.updateRoom(updatedHotel)
                  .pipe(finalize(() => this.isLoading = false))
                  .subscribe({
                    next: () => {
                      this.loadCustomer();
                      this.closeForm();
                    },
                    error: (error) => {
                      console.error('Error al actualizar el usuario:', error);
                    }
                  });
              }
            }

            deleteHotel(id: number): void {
              if (confirm('¿Estás seguro de eliminar este usuario?')) {
                this.isLoading = true;
                this.typeRoomService.deleteRoom(id)
                  .pipe(finalize(() => this.isLoading = false))
                  .subscribe({
                    next: () => this.loadCustomer(),
                    error: (error) => {
                      console.error('Error al eliminar el usuario:', error);
                    }
                  });
              }
            }



    


}
