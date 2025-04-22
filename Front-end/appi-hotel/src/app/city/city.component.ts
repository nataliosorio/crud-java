import { Component, OnInit } from '@angular/core';
import { City, ServiceCityService } from '../service-city.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-city',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './city.component.html',
  styleUrl: './city.component.css'
})
export class CityComponent implements OnInit{

  cities: City[] = [];
  cityForm!: FormGroup;
  showForm: boolean = false;
  editIndex: number | null = null;
  isLoading: boolean = false;

  constructor(private cityService: ServiceCityService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadCities();
    this.cityForm = this.fb.group({
      name: ['', Validators.required]
    });
  }

  // Método para cargar/recargar ciudades
  loadCities(): void {
    this.isLoading = true;
    this.cityService.getCities()
      .pipe(finalize(() => this.isLoading = false))
      .subscribe({
        next: (data) => {
          console.log('Ciudades cargadas:', data);
          this.cities = [...data]; // Creamos una nueva referencia del array
        },
        error: (error) => {
          console.error('Error al cargar ciudades:', error);
        }
      });
  }

  refrescar() :void {
    this.loadCities();
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    this.cityForm.reset();
    this.editIndex = null;
  }

  closeForm(): void {
    this.showForm = false;
    this.cityForm.reset();
    this.editIndex = null;
  }

  trackByCityId(index: number, city: City): number {
    return city.id;
  }

  submitForm(): void {
    if (this.cityForm.valid) {
      const newCity: Omit<City, 'id'> = {
        name: this.cityForm.value.name
      };

      this.isLoading = true;
      this.cityService.addCity(newCity)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: (response) => {
            console.log('Ciudad añadida:', response);
            this.loadCities(); // Recargar la lista después de añadir
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al añadir ciudad:', error);
          }
        });
    }
  }

  deleteCity(id: number): void {
    if (confirm('¿Estás seguro de que deseas eliminar esta ciudad?')) {
      this.isLoading = true;
      this.cityService.deleteCity(id)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: () => {
            console.log('Ciudad eliminada con ID:', id);
            this.loadCities(); // Recargar la lista después de eliminar
          },
          error: (error) => {
            console.error('Error al eliminar ciudad:', error);
          }
        });
    }
  }

  editCity(index: number): void {
    this.editIndex = index;
    const city = this.cities[index];
    this.cityForm.patchValue({ name: city.name });
    this.showForm = true;
  }

  updateCity(): void {
    if (this.cityForm.valid && this.editIndex !== null) {
      const updatedCity: City = {
        id: this.cities[this.editIndex].id,
        name: this.cityForm.value.name
      };

      this.isLoading = true;
      this.cityService.updateCity(updatedCity)
        .pipe(finalize(() => this.isLoading = false))
        .subscribe({
          next: (response) => {
            console.log('Ciudad actualizada:', response);
            this.loadCities(); // Recargar la lista después de actualizar
            this.closeForm();
          },
          error: (error) => {
            console.error('Error al actualizar ciudad:', error);
          }
        });
    }
  }
}
