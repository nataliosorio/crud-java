import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

export interface Room {
  id: number;
  name: string;
  status:string
  roomNumber: number;
  idHotel: number;
  hotelName: string;
  idTypeRoom: number;
  roomTypeName: string;
}



@Injectable({
  providedIn: 'root'
})
export class ServiceRoomService {
  private apiUrl = 'http://localhost:8080/api/v1/Room/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  // Método para manejar errores HTTP
      private handleError(error: HttpErrorResponse) {
        console.error('Error HTTP:', error);
        return throwError(() => new Error('Ocurrió un error en la petición. Por favor intenta de nuevo.'));
      }
    
      getRooms(): Observable<Room[]> {
        return this.http.get<Room[]>(this.apiUrl)
          .pipe(catchError(this.handleError));
      }
    
      addRoom(city: Omit<Room, 'id'>): Observable<Room> {
        return this.http.post<Room>(this.apiUrl, city)
          .pipe(catchError(this.handleError));
      }
    
      updateRoom(city: Room): Observable<Room> {
        return this.http.put<Room>(`${this.apiUrl}${city.id}`, city)
          .pipe(catchError(this.handleError));
      }
    
      deleteRoom(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}${id}`)
          .pipe(catchError(this.handleError));
      }
}
