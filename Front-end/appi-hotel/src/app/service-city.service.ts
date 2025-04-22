import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';


export interface City {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceCityService {

  private apiUrl = 'http://localhost:8080/api/v1/City/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
  private handleError(error: HttpErrorResponse) {
    console.error('Error HTTP:', error);
    return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
  }

  getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addCity(city: Omit<City, 'id'>): Observable<City> {
    return this.http.post<City>(this.apiUrl, city)
      .pipe(catchError(this.handleError));
  }

  updateCity(city: City): Observable<City> {
    return this.http.put<City>(`${this.apiUrl}${city.id}`, city)
      .pipe(catchError(this.handleError));
  }

  deleteCity(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`)
      .pipe(catchError(this.handleError));
  }
}
