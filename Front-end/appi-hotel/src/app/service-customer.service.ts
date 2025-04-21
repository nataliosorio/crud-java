import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Customer {
  id: number;
  firstName: string;
  lastName: string;
  documentName: string;
  documentNumber: string;
  email: number;
  phone: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceCustomerService {

  private apiUrl = 'http://localhost:8080/api/v1/Customer/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getHotel(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }
}
