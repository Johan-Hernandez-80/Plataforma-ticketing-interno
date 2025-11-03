import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallNotificationListItemComponent } from './small-notification-list-item.component';

describe('SmallNotificationListItemComponent', () => {
  let component: SmallNotificationListItemComponent;
  let fixture: ComponentFixture<SmallNotificationListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SmallNotificationListItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SmallNotificationListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
