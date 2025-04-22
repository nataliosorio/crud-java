import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';


export interface DocumentType {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceDocumentTypeService {

  private apiUrl = 'http://localhost:8080/api/v1/DocumentType/'; 

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
  private handleError(error: HttpErrorResponse) {
    console.error('Error HTTP:', error);
    return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
  }

  getCities(): Observable<DocumentType[]> {
    return this.http.get<DocumentType[]>(this.apiUrl)
      .pipe(catchError(this.handleError));
  }

  addCity(city: Omit<DocumentType, 'id'>): Observable<DocumentType> {
    return this.http.post<DocumentType>(this.apiUrl, city)
      .pipe(catchError(this.handleError));
  }

  updateCity(city: DocumentType): Observable<DocumentType> {
    return this.http.put<DocumentType>(`${this.apiUrl}${city.id}`, city)
      .pipe(catchError(this.handleError));
  }

  deleteCity(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}${id}`)
      .pipe(catchError(this.handleError));
  }
}
