import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Invoice {
  id: number;
  reservationId: number;
  subtotal: number;
  tax: number;
  total: number;
  paymentStatus: string;
  issueDate: Date;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceInvoiceService {

  private apiUrl = 'http://localhost:8080/api/v1/Invoice/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getHotel(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.apiUrl);
  }
}
