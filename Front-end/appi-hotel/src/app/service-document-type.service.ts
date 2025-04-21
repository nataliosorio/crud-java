import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface DocumentType {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiceDocumentTypeService {

  private apiUrl = 'http://localhost:8080/api/v1/DocumentType/'; 

  constructor(private http: HttpClient) {}

  getDocument(): Observable<DocumentType[]> {
    return this.http.get<DocumentType[]>(this.apiUrl);
  }
}
