import { TestBed } from '@angular/core/testing';

import { ServiceReservationService } from './service-reservation.service';

describe('ServiceReservationService', () => {
  let service: ServiceReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
