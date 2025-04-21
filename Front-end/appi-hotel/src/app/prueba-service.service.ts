import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PruebaServiceService {
  private baseUrl = environment.apiURL;

  constructor(private http: HttpClient) {}

  getDataFromTable(tableName: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}${tableName}`);
  }
  // postDataToTable(tableName: string, data: any): Observable<any> {
  //   return this.http.post<any>(`${this.baseUrl}${tableName}`, data);
  // }

  postDataToTable(tableName: string, body: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}${tableName}`, body);
  }


}
