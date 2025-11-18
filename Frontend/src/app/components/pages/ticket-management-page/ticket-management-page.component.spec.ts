import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketManagementPageComponent } from './ticket-management-page.component';

describe('TicketManagementPageComponent', () => {
  let component: TicketManagementPageComponent;
  let fixture: ComponentFixture<TicketManagementPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketManagementPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
