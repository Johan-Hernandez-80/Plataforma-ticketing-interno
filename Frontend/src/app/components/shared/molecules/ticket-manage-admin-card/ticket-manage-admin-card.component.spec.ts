import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketManageAdminCardComponent } from './ticket-manage-admin-card.component';

describe('TicketManageAdminCardComponent', () => {
  let component: TicketManageAdminCardComponent;
  let fixture: ComponentFixture<TicketManageAdminCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketManageAdminCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketManageAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
