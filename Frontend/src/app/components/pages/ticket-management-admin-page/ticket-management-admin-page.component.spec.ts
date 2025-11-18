import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketManagementAdminPageComponent } from './ticket-management-admin-page.component';

describe('TicketManagementAdminPageComponent', () => {
  let component: TicketManagementAdminPageComponent;
  let fixture: ComponentFixture<TicketManagementAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketManagementAdminPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketManagementAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
