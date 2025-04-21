import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Reservation {
  id: number;
  customerId: number;
  customerFullName: string;
  employeeId: number;
  employeeFullName: string;
  numberday: number;
  numberNight: number;
  status: string;
  notes: string;
  createdAt: Date;
  rooms: Room[];
  invoice: Invoice;
}

export interface Room {
  id: number;
  roomId: number;
  roomNumber: string;
  appliedPrice: number;
}

export interface Invoice {
  id: number;
  reservationId: number;
  subtotal: number;
  tax: number;
  total: number;
  paymentStatus: string;
  issueDate: Date; // o Date si prefieres
}

@Injectable({
  providedIn: 'root'
})
export class ServiceReservationService {

  private apiUrl = 'http://localhost:8080/api/v1/Reservation/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getHotel(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl);
  }
}
