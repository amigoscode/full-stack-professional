import { TestBed } from '@angular/core/testing';

import { AccessGuardService } from './access-guard.service';

describe('AccessGuardService', () => {
  let service: AccessGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccessGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
