import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface Hotel {
  id: number;
  name: string;
  address: string;
  phone: string;
  email: string;
  idCity: number; 
  cityName: string;
}

export interface HotelCreate {
  id: number;
  name: string;
  address: string;
  phone: string;
  email: string;
  idCity: number; 
}

@Injectable({
  providedIn: 'root'
})
export class ServiceHotelService {

  private apiUrl = 'http://localhost:8080/api/v1/Hotel/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

   // Método para manejar errores HTTP
   private handleError(error: HttpErrorResponse) {
    console.error('Error HTTP:', error);
    return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
  }

  getRooms(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addRoom(city: Omit<Hotel, 'id'>): Observable<Hotel> {
    return this.http.post<Hotel>(this.apiUrl, city)
      .pipe(catchError(this.handleError));
  }

  updateRoom(city: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(`${this.apiUrl}${city.id}`, city)
      .pipe(catchError(this.handleError));
  }

  deleteRoom(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`)
      .pipe(catchError(this.handleError));
  }
}
