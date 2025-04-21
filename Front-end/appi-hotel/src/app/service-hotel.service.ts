import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Hotel {
  id: number;
  name: string;
  address: string;
  phone: string;
  email: string;
  starts: number;
  cityName: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceHotelService {

  private apiUrl = 'http://localhost:8080/api/v1/Hotel/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getHotel(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.apiUrl);
  }
}
