import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioDetailPageComponent } from './usuario-detail-page.component';

describe('UsuarioDetailPageComponent', () => {
  let component: UsuarioDetailPageComponent;
  let fixture: ComponentFixture<UsuarioDetailPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioDetailPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UsuarioDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
