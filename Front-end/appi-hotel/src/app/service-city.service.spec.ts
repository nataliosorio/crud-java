import { TestBed } from '@angular/core/testing';

import { ServiceCityService } from './service-city.service';

describe('ServiceCityService', () => {
  let service: ServiceCityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceCityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
