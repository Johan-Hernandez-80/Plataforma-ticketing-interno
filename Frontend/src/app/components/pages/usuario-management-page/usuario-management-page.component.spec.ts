import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioManagementPageComponent } from './usuario-management-page.component';

describe('UsuarioManagementPageComponent', () => {
  let component: UsuarioManagementPageComponent;
  let fixture: ComponentFixture<UsuarioManagementPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioManagementPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UsuarioManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
