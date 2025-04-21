import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface City {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceCityService {

  private apiUrl = 'http://localhost:8080/api/v1/City/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getCities(): Observable<City[]> {
    return this.http.get<City[]>(this.apiUrl);
  }
}
