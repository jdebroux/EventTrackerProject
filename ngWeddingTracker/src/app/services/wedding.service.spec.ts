import { TestBed } from '@angular/core/testing';

import { WeddingService } from './wedding.service';

describe('WeddingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WeddingService = TestBed.get(WeddingService);
    expect(service).toBeTruthy();
  });
});
