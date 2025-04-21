import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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

  getTypeRoom(): Observable<typeRoom[]> {
    return this.http.get<typeRoom[]>(this.apiUrl);
  }
}
