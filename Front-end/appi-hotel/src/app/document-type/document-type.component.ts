import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DocumentType, ServiceDocumentTypeService } from '../service-document-type.service';

@Component({
  selector: 'app-document-type',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './document-type.component.html',
  styleUrl: './document-type.component.css'
})
export class DocumentTypeComponent implements OnInit {
 document: DocumentType [] = [];

  constructor(private cityService: ServiceDocumentTypeService) {}

  ngOnInit(): void {
    this.cityService.getDocument().subscribe(data => {
      this.document = data;
      console.log(this.document);
    });
  }
}
