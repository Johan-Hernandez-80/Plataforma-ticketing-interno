import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioCreatePageComponent } from './usuario-create-page.component';

describe('UsuarioCreatePageComponent', () => {
  let component: UsuarioCreatePageComponent;
  let fixture: ComponentFixture<UsuarioCreatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioCreatePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UsuarioCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
