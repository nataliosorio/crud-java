import { Component, OnInit } from '@angular/core';
import { Hotel, ServiceHotelService } from '../service-hotel.service';
import { CommonModule } from '@angular/common';
import { finalize } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { City, ServiceCityService } from '../service-city.service';

@Component({
  selector: 'app-hotel',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './hotel.component.html',
  styleUrl: './hotel.component.css'
})
export class HotelComponent implements OnInit {

  hotels: Hotel[] = [];
  city: City[] = [];

  hotelForm!: FormGroup;
  showForm: boolean = false;
  editIndex: number | null = null;
  isLoading: boolean = false;

  constructor(private hotelService: ServiceHotelService, private cityService: ServiceCityService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadHotels();
    this.loadCity();
    this.hotelForm = this.fb.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      idCity: [null, Validators.required] // <- este campo es obligatorio para habilitar el botón
    });
  }

  loadHotels(): void {
    this.isLoading = true;
    this.hotelService.getRooms()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Hoteles cargados:', data);
          this.hotels = [...data];
        },
        error: (error) => {
          console.error('Error al cargar hoteles:', error);
        }
      });
  }

  loadCity(): void {
    this.cityService.getCities().subscribe({
      next: (data) => {
        this.city = data;
      },
      error: (err) => console.error('Error al cargar las ciudades:', err)
    });
  }

  refrescar(): void {
    this.loadHotels();
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    this.hotelForm.reset();
    this.editIndex = null;
  }

  closeForm(): void {
    this.showForm = false;
    this.hotelForm.reset();
    this.editIndex = null;
  }

  trackByHotelId(index: number, hotel: Hotel): number {
    return hotel.id;
  }

  submitForm(): void {
    if (this.hotelForm.valid) {
      const newHotel: Omit<Hotel, 'id'> = this.hotelForm.value;

      this.isLoading = true;
      this.hotelService.addRoom(newHotel)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadHotels();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al agregar hotel:', error);
          }
        });
    }
  }

  editHotel(index: number): void {
    const hotel = this.hotels[index];
    this.hotelForm.patchValue(hotel);
    this.showForm = true;
    this.editIndex = index;
  }

  updateHotel(): void {
    if (this.hotelForm.valid && this.editIndex !== null) {
      const updatedHotel: Hotel = {
        ...this.hotels[this.editIndex],
        ...this.hotelForm.value
      };

      this.isLoading = true;
      this.hotelService.updateRoom(updatedHotel)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            this.loadHotels();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al actualizar hotel:', error);
          }
        });
    }
  }

  deleteHotel(id: number): void {
    if (confirm('¿Estás seguro de eliminar este hotel?')) {
      this.isLoading = true;
      this.hotelService.deleteRoom(id)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => this.loadHotels(),
          error: (error) => {
            console.error('Error al eliminar hotel:', error);
          }
        });
    }
  }
}
