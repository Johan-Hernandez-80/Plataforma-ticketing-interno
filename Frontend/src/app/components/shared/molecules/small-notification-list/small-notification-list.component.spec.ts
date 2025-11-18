import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallNotificationListComponent } from './small-notification-list.component';

describe('SmallNotificationListComponent', () => {
  let component: SmallNotificationListComponent;
  let fixture: ComponentFixture<SmallNotificationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SmallNotificationListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SmallNotificationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
