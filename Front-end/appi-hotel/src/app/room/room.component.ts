import { Component, OnInit } from '@angular/core';
import { Room, ServiceRoomService } from '../service-room.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';
import { ServiceTyperoomService, typeRoom } from '../service-typeroom.service';
import { Hotel, ServiceHotelService } from '../service-hotel.service';

@Component({
  selector: 'app-room',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './room.component.html',
  styleUrl: './room.component.css'
})
export class RoomComponent implements OnInit {
  rooms: Room[] = [];
  typeRoom: typeRoom [] = [];
  hotel: Hotel [] = [];

  roomForm!: FormGroup;
  showForm: boolean = false;
  editIndex: number | null = null;
  isLoading: boolean = false;

  constructor(private roomService: ServiceRoomService, private typeRoomService: ServiceTyperoomService, private serviceHotel: ServiceHotelService,private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadRooms();
    this.loadHotel();
    this.loadTypeRoom();
    this.roomForm = this.fb.group({
      name: ['', Validators.required],
      status: ['', Validators.required],
      roomNumber: ['', [Validators.required, Validators.min(1)]],
      idHotel: [null, Validators.required],
      idTypeRoom: [null, Validators.required]
    });
  }

  // Cargar habitaciones
  loadRooms(): void {
    this.isLoading = true;
    this.roomService.getRooms()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Habitaciones cargadas:', data);
          this.rooms = [...data];
        },
        error: (error) => {
          console.error('Error al cargar habitaciones:', error);
        }
      });
  }

   // Cargar lista de typeRoom para el select
   loadTypeRoom(): void {
    this.typeRoomService.getCities().subscribe({
      next: (data) => {
        this.typeRoom = data;
      },
      error: (err) => console.error('Error al cargar los  tipos de habitacion:', err)
    });
  }

  loadHotel(): void {
    this.serviceHotel.getRooms().subscribe({
      next: (data) => {
        this.hotel = data;
      },
      error: (err) => console.error('Error al cargar los  hoteles:', err)
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

  trackByRoomId(index: number, room: Room): number {
    return room.id;
  }


  submitForm(): void {
    if (this.roomForm.valid) {
      const newHotel: Omit<Room, 'id'> = this.roomForm.value;

      this.isLoading = true;
      this.roomService.addRoom(newHotel)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadRooms();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al agregar la habitación:', error);
          }
        });
    }
  }

 
  editRoom(index: number): void {
    const hotel = this.rooms[index];
    this.roomForm.patchValue(hotel);
    this.showForm = true;
    this.editIndex = index;
  }

  updateRoom(): void {
    if (this.roomForm.valid && this.editIndex !== null) {
      const updatedRoom: Room = {
        ...this.rooms[this.editIndex],
        ...this.roomForm.value
      };

      this.isLoading = true;
      this.roomService.updateRoom(updatedRoom)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadRooms();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al actualizar habitación:', error);
          }
        });
    }
  }



  deleteRoom(id: number): void {
    this.isLoading = true;
    this.roomService.deleteRoom(id)
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: () => {
          this.rooms = this.rooms.filter(room => room.id !== id);
        },
        error: (error) => {
          console.error('Error al eliminar habitación:', error);
        }
      });
  }
}
