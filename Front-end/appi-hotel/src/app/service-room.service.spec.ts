import { TestBed } from '@angular/core/testing';

import { ServiceRoomService } from './service-room.service';

describe('ServiceRoomService', () => {
  let service: ServiceRoomService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceRoomService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
