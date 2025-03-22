import { TestBed } from '@angular/core/testing';

import { TogglecartService } from './togglecart.service';

describe('TogglecartService', () => {
  let service: TogglecartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TogglecartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
