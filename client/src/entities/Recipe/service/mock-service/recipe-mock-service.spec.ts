import { TestBed } from '@angular/core/testing';

import { RecipeMockService } from './recipe-mock-service';

describe('RecipeMockService', () => {
  let service: RecipeMockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecipeMockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
