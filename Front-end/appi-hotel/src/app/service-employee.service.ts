import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface Employee {
  id: number;
  firstName: string;
  lastName: string;
  idDocumentType: number;
  documentTypeName: string;
  documentNumber: string;
  email: number;
  phone: string;
  idRole: number;
  rolName: string;
  idHotel: number;
  hotelName: string;

}

@Injectable({
  providedIn: 'root'
})
export class ServiceEmployeeService {

  private apiUrl = 'http://localhost:8080/api/v1/Employee/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

     // Método para manejar errores HTTP
     private handleError(error: HttpErrorResponse) {
      console.error('Error HTTP:', error);
      return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
    }
  
    getRooms(): Observable<Employee[]> {
      return this.http.get<Employee[]>(this.apiUrl)
        .pipe(catchError(this.handleError));
    }
  
    addRoom(city: Omit<Employee, 'id'>): Observable<Employee> {
      return this.http.post<Employee>(this.apiUrl, city)
        .pipe(catchError(this.handleError));
    }
  
    updateRoom(city: Employee): Observable<Employee> {
      return this.http.put<Employee>(`${this.apiUrl}${city.id}`, city)
        .pipe(catchError(this.handleError));
    }
  
    deleteRoom(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}${id}`)
        .pipe(catchError(this.handleError));
    }
}
