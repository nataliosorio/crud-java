import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface Customer {
  id: number;
  firstName: string;
  lastName: string;
  idDocumentType :number;
  documentName: string;
  documentNumber: string;
  phone: string;
  email: number;

}

@Injectable({
  providedIn: 'root'
})
export class ServiceCustomerService {

  private apiUrl = 'http://localhost:8080/api/v1/Customer/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
  private handleError(error: HttpErrorResponse) {
    console.error('Error HTTP:', error);
    return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
  }

  getRooms(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addRoom(city: Omit<Customer, 'id'>): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, city)
      .pipe(catchError(this.handleError));
  }

  updateRoom(city: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}${city.id}`, city)
      .pipe(catchError(this.handleError));
  }

  deleteRoom(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`)
      .pipe(catchError(this.handleError));
  }
}
