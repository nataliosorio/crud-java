import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface Rol {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceRoleService {
  private apiUrl = 'http://localhost:8080/api/v1/Rol/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
  private handleError(error: HttpErrorResponse) {
    console.error('Error HTTP:', error);
    return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
  }

  getRol(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addRol(city: Omit<Rol, 'id'>): Observable<Rol> {
    return this.http.post<Rol>(this.apiUrl, city)
      .pipe(catchError(this.handleError));
  }

  updateRol(city: Rol): Observable<Rol> {
    return this.http.put<Rol>(`${this.apiUrl}${city.id}`, city)
      .pipe(catchError(this.handleError));
  }

  deleteRol(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`)
      .pipe(catchError(this.handleError));
  }
}
