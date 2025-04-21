import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Rol {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceRoleService {
  private apiUrl = 'http://localhost:8080/api/v1/Rol/'; // cambia esto según tu URL real

  constructor(private http: HttpClient) {}

  getRol(): Observable<Rol[]> {
    return this.http.get<Rol[]>(this.apiUrl);
  }
}
