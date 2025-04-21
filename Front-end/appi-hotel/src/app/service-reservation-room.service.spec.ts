import { TestBed } from '@angular/core/testing';

import { ServiceReservationRoomService } from './service-reservation-room.service';

describe('ServiceReservationRoomService', () => {
  let service: ServiceReservationRoomService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceReservationRoomService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
