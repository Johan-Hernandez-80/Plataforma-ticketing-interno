import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LargeNotificationListCardComponent } from './large-notification-list-card.component';

describe('LargeNotificationListCardComponent', () => {
  let component: LargeNotificationListCardComponent;
  let fixture: ComponentFixture<LargeNotificationListCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LargeNotificationListCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LargeNotificationListCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
