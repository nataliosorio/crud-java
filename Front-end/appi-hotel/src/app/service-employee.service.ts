import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Employee {
  id: number;
  firstName: string;
  lastName: string;
  documentTypeName: string;
  documentNumber: string;
  email: number;
  phone: string;
  rolName: string;
  hotelName: string;

}

@Injectable({
  providedIn: 'root'
})
export class ServiceEmployeeService {

  private apiUrl = 'http://localhost:8080/api/v1/Employee/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getHotel(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiUrl);
  }
}
