import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestEndpointsComponent } from './test-endpoints.component';

describe('TestEndpointsComponent', () => {
  let component: TestEndpointsComponent;
  let fixture: ComponentFixture<TestEndpointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestEndpointsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TestEndpointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
