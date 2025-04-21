import { TestBed } from '@angular/core/testing';

import { ServiceRoleService } from './service-role.service';

describe('ServiceRoleService', () => {
  let service: ServiceRoleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceRoleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
