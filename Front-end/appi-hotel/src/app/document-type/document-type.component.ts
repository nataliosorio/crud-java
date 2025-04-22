import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DocumentType, ServiceDocumentTypeService } from '../service-document-type.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-document-type',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './document-type.component.html',
  styleUrl: './document-type.component.css'
})
export class DocumentTypeComponent implements OnInit {
  documentForm!: FormGroup;
  documentTypes: DocumentType[] = [];
  isLoading = false;
  showForm = false;
  editIndex: number | null = null;

  constructor(
    private fb: FormBuilder,
    private documentTypeService: ServiceDocumentTypeService
  ) {}

  ngOnInit(): void {
    this.getDocumentTypes();
    this.documentForm = this.fb.group({
      name: ['', Validators.required],
    });
  }

 

  toggleForm() {
    this.showForm = !this.showForm;
    if (!this.showForm) {
      this.documentForm.reset();
      this.editIndex = null;
    }
  }

  getDocumentTypes(): void {
    this.isLoading = true;
    this.documentTypeService.getCities()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Tipos de documentos cargados:', data);
          this.documentTypes = [...data];
        },
        error: (error) => {
          console.error('Error al cargar tipos de documentos:', error);
        }
      });
  }

  refrescar() {
    this.getDocumentTypes();
  }

  closeForm(): void {
    this.showForm = false;
    this.documentForm.reset();
    this.editIndex = null;
  }

  submitForm(): void {
      if (this.documentForm.valid) {
        const newRoom: Omit<DocumentType, 'id'> = {
          name: this.documentForm.value.name,
         
        };
    
        this.isLoading = true;
        this.documentTypeService.addCity(newRoom)
          .pipe(finalize(() => this.isLoading = false))
          .subscribe({
            next: (room) => {
              this.documentTypes.push(room);
              this.closeForm();
            },
            error: (err) => {
              console.error('Error al agregar tipo de documento:', err);
            }
          });
      }
    }

 
  editDocumentType(index: number): void {
    const room = this.documentTypes[index];
    this.documentForm.setValue({
      name: room.name
    });
    this.editIndex = index;
    this.showForm = true;
  }
  

  updateDocumentType(): void {
      if (this.editIndex !== null && this.documentForm.valid) {
        const updatedRoom: DocumentType = {
          id: this.documentTypes[this.editIndex].id,
          ...this.documentForm.value
        };
    
        this.isLoading = true;
        this.documentTypeService.updateCity(updatedRoom)
          .pipe(finalize(() => this.isLoading = false))
          .subscribe({
            next: () => {
              this.getDocumentTypes();
              this.documentTypes[this.editIndex!] = updatedRoom;
              this.closeForm();
            },
            error: (err) => {
              console.error('Error al actualizar tipo de documento:', err);
            }
          });
      }
    }

 deleteCity(id: number): void {
       if (confirm('¿Estás seguro de que deseas eliminar este tipo de documento?')) {
         this.isLoading = true;
         this.documentTypeService.deleteCity(id)
           .pipe(finalize(() => this.isLoading = false))
           .subscribe({
             next: () => {
               console.log('Habitacion eliminada con ID:', id);
               this.getDocumentTypes(); // Recargar la lista después de eliminar
             },
             error: (error) => {
               console.error('Error al eliminartipo de habitacion:', error);
             }
           });
       }
  }
}
