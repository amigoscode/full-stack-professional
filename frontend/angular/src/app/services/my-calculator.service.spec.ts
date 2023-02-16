import { TestBed } from '@angular/core/testing';

import { MyCalculatorService } from './my-calculator.service';

describe('MyCalculatorService', () => {
  let service: MyCalculatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyCalculatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
