import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ServiceTyperoomService, typeRoom } from '../service-typeroom.service';
import { finalize } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-type-room',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './type-room.component.html',
  styleUrl: './type-room.component.css'
})
export class TypeRoomComponent implements OnInit{
//  typeRoom: typeRoom [] = [];

//   constructor(private typeRoomService: ServiceTyperoomService) {}

//   ngOnInit(): void {
//     this.typeRoomService.getTypeRoom().subscribe(data => {
//       this.typeRoom = data;
//       console.log(this.typeRoom);
//     });
//   }


rooms: typeRoom[] = [];
roomForm!: FormGroup;
showForm: boolean = false;
editIndex: number | null = null;
isLoading: boolean = false;

  constructor(private roomService: ServiceTyperoomService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadRooms();
    this.roomForm = this.fb.group({
      name: ['', Validators.required],
      priceDay: [0, [Validators.required, Validators.min(0)]],
      priceNight: [0, [Validators.required, Validators.min(0)]]
    });
  }
  
  // Método para cargar/recargar tipos de habitación
  loadRooms(): void {
    this.isLoading = true;
    this.roomService.getCities()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Tipos de habitación cargados:', data);
          this.rooms = [...data];
        },
        error: (error) => {
          console.error('Error al cargar tipos de habitación:', error);
        }
      });
  }
  
  refrescar(): void {
    this.loadRooms();
  }
  
  toggleForm(): void {
    this.showForm = !this.showForm;
    this.roomForm.reset();
    this.editIndex = null;
  }
  
  closeForm(): void {
    this.showForm = false;
    this.roomForm.reset();
    this.editIndex = null;
  }
  
  trackByRoomId(index: number, room: typeRoom): number {
    return room.id;
  }
  
  submitForm(): void {
    if (this.roomForm.valid) {
      const newRoom: Omit<typeRoom, 'id'> = {
        name: this.roomForm.value.name,
        priceDay: this.roomForm.value.priceDay,
        priceNight: this.roomForm.value.priceNight
      };
  
      this.isLoading = true;
      this.roomService.addCity(newRoom)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: (room) => {
            this.rooms.push(room);
            this.closeForm();
          },
          error: (err) => {
            console.error('Error al agregar tipo de habitación:', err);
          }
        });
    }
  }
  
  editRoom(index: number): void {
    const room = this.rooms[index];
    this.roomForm.setValue({
      name: room.name,
      priceDay: room.priceDay,
      priceNight: room.priceNight
    });
    this.editIndex = index;
    this.showForm = true;
  }
  
  updateRoom(): void {
    if (this.editIndex !== null && this.roomForm.valid) {
      const updatedRoom: typeRoom = {
        id: this.rooms[this.editIndex].id,
        ...this.roomForm.value
      };
  
      this.isLoading = true;
      this.roomService.updateCity(updatedRoom)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.rooms[this.editIndex!] = updatedRoom;
            this.closeForm();
          },
          error: (err) => {
            console.error('Error al actualizar tipo de habitación:', err);
          }
        });
    }
  }

  
    deleteCity(id: number): void {
      if (confirm('¿Estás seguro de que deseas eliminar este tipo de habitacion?')) {
        this.isLoading = true;
        this.roomService.deleteCity(id)
          .pipe(finalize(() => this.isLoading = false))
          .subscribe({
            next: () => {
              console.log('Habitacion eliminada con ID:', id);
              this.loadRooms(); // Recargar la lista después de eliminar
            },
            error: (error) => {
              console.error('Error al eliminartipo de habitacion:', error);
            }
          });
      }
    }
}