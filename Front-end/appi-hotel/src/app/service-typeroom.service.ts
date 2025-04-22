import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface typeRoom {
  id: number;
  name: string;
  priceDay: number;
  priceNight: number;
}


@Injectable({
  providedIn: 'root'
})
export class ServiceTyperoomService {

  private apiUrl = 'http://localhost:8080/api/v1/typeRoom/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
    private handleError(error: HttpErrorResponse) {
      console.error('Error HTTP:', error);
      return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
    }
  
    getCities(): Observable<typeRoom[]> {
      return this.http.get<typeRoom[]>(this.apiUrl)
        .pipe(catchError(this.handleError));
    }
  
    addCity(city: Omit<typeRoom, 'id'>): Observable<typeRoom> {
      return this.http.post<typeRoom>(this.apiUrl, city)
        .pipe(catchError(this.handleError));
    }
  
    updateCity(city: typeRoom): Observable<typeRoom> {
      return this.http.put<typeRoom>(`${this.apiUrl}${city.id}`, city)
        .pipe(catchError(this.handleError));
    }
  
    deleteCity(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}${id}`)
        .pipe(catchError(this.handleError));
    }
}
