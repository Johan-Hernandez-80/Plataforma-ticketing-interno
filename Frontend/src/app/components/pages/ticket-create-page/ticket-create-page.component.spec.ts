import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TicketCreatePageComponent } from './ticket-create-page.component';

describe('TicketCreatePageComponent', () => {
  let component: TicketCreatePageComponent;
  let fixture: ComponentFixture<TicketCreatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketCreatePageComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(TicketCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
