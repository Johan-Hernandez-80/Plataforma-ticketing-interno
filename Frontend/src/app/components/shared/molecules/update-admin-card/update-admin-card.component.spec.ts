import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAdminCardComponent } from './update-admin-card.component';

describe('UpdateAdminCardComponent', () => {
  let component: UpdateAdminCardComponent;
  let fixture: ComponentFixture<UpdateAdminCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateAdminCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
