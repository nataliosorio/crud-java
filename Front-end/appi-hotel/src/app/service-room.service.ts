import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Room {
  id: number;
  name: string;
  status:string
  roomNumber: number;
  hotelName: string;
  roomTypeName: string;
}


@Injectable({
  providedIn: 'root'
})
export class ServiceRoomService {
  private apiUrl = 'http://localhost:8080/api/v1/Room/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getRoom(): Observable<Room[]> {
    return this.http.get<Room[]>(this.apiUrl);
  }
}
