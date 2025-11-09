import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationListPageComponent } from './notification-list-page.component';

describe('NotificationListPageComponent', () => {
  let component: NotificationListPageComponent;
  let fixture: ComponentFixture<NotificationListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationListPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotificationListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
