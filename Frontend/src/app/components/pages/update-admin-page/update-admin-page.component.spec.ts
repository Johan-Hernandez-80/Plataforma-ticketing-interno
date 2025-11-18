import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAdminPageComponent } from './update-admin-page.component';

describe('UpdateAdminPageComponent', () => {
  let component: UpdateAdminPageComponent;
  let fixture: ComponentFixture<UpdateAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateAdminPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
