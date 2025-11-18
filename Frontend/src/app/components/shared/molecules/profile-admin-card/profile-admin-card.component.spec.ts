import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileAdminCardComponent } from './profile-admin-card.component';

describe('ProfileAdminCardComponent', () => {
  let component: ProfileAdminCardComponent;
  let fixture: ComponentFixture<ProfileAdminCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileAdminCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfileAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
