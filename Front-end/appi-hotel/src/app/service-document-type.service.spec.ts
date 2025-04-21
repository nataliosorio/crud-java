import { TestBed } from '@angular/core/testing';

import { ServiceDocumentTypeService } from './service-document-type.service';

describe('ServiceDocumentTypeService', () => {
  let service: ServiceDocumentTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceDocumentTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
